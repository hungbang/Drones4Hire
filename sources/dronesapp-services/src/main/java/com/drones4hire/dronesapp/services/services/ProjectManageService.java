package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.*;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.*;
import com.drones4hire.dronesapp.models.db.projects.*;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;
import com.drones4hire.dronesapp.services.services.util.CSVWriter;
import com.drones4hire.dronesapp.services.services.util.model.csv.ProjectCSVModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectManageService
{

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Autowired
	private AttachmentMapper attachMapper;

	@Autowired
	private BidMapper bidMapper;

	@Autowired
	private AWSEmailService emailService;

	@Transactional(readOnly = true)
	public SearchResult<ProjectSearchResult> searchProjectsWithAdmin(ProjectSearchCriteriaForAdmin sc) throws
			ServiceException
	{
		SearchResult<ProjectSearchResult> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		List<ProjectSearchResult> projectSearchResults = projectMapper.searchProjectsWithAdmin(sc);
		results.setTotalResults(projectMapper.getProjectsWithAdminSearchCount(sc));
		results.setResults(projectSearchResults);
		return results;
	}

	@Transactional(readOnly = true)
	public SearchResult<ProjectOnMap> searchProjectsForMap(ProjectForMapSearchCriteria sc) throws ServiceException
	{
		SearchResult<ProjectOnMap> results = new SearchResult<>();
		List<ProjectOnMap> projectSearchResults = projectMapper.searchProjectsForMap(sc);
		results.setTotalResults(projectMapper.getProjectsForMapSearchCount(sc));
		results.setResults(projectSearchResults);
		results.setPageSize(results.getTotalResults());
		return results;
	}

	@Transactional(readOnly = true)
	public void exportProjectsToCSV(ProjectSearchCriteria sc, Writer writer)
			throws ServiceException, IOException
	{
		sc.setPage(null);
		sc.setPageSize(null);

		List<ProjectCSVModel> projects = new ArrayList<>();
		List<ProjectSearchResult> results = projectMapper.searchProjects(sc);
		ProjectCSVModel projectCSVModel;
		for (ProjectSearchResult result : results)
		{
			projectCSVModel = new ProjectCSVModel();

			projectCSVModel.setId(result.getProject().getId());
			projectCSVModel.setTitle(result.getProject().getTitle());
			if (result.getPilot() != null)
			{
				projectCSVModel.setPilotEmail(result.getPilot().getEmail());
			}
			projectCSVModel.setClientEmail(result.getClient().getEmail());
			if (result.getProject().getDuration() != null)
			{
				projectCSVModel.setDuration(result.getProject().getDuration().getTitle());
			}
			projectCSVModel.setBudget(result.getProject().getBudget().getTitle());
			projectCSVModel.setStatus(result.getProject().getStatus());
			projectCSVModel.setCountry(result.getProject().getLocation().getCountry().getName());
			projectCSVModel.setCity(result.getProject().getLocation().getCity());
			projectCSVModel.setService(result.getProject().getService().getName());
			projectCSVModel.setStartDate(result.getProject().getStartDate());
			if (result.getProject().getFinishDate() != null)
			{
				projectCSVModel.setFinishDate(result.getProject().getFinishDate());
			}
			projectCSVModel.setCreatedAt(result.getProject().getCreatedAt());

			projects.add(projectCSVModel);
		}
		CSVWriter.exportProjectsToCSV(projects, writer);
	}

	@Transactional(rollbackFor = Exception.class)
	public Comment createComment(Comment comment, long principalId) throws ServiceException
	{
		User user = userService.getUserById(principalId);
		comment.setUser(user);
		commentMapper.createComment(comment);
		Project project = projectMapper.getProjectById(comment.getProjectId());
		emailService.sendNewCommentReceiveEmail(project);
		return commentMapper.getCommentById(comment.getId());
	}

	@Transactional(readOnly = true)
	public List<Comment> getCommentsByProjectId(long projectId) throws ServiceException
	{
		return commentMapper.getCommentsByProjectId(projectId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Comment updateComment(Comment comment) throws ServiceException
	{
		commentMapper.updateComment(comment);
		return comment;
	}

	@Transactional(rollbackFor = Exception.class)
	public Feedback createFeedback(Feedback feedback) throws ServiceException
	{
		Project project = projectService.getProjectById(feedback.getProjectId());
		List<Feedback> feedbacks = feedbackMapper.getFeedbacksByProjectId(project.getId());
		if (!feedback.getToUser().getId()
				.equals(project.getPilotId())
				|| !project.getStatus().equals(Project.Status.COMPLETED) || !feedbacks.isEmpty())
		{
			throw new ForbiddenOperationException();
		}
		User toUser = userService.getUserById(feedback.getToUser().getId());
		feedbackMapper.createFeedback(feedback);
		toUser.setRating(calculateUserRating(toUser).doubleValue());
		userService.updateUser(toUser);
		return feedback;
	}

	@Transactional(rollbackFor = Exception.class)
	public Attachment createAttachment(Attachment attachment) throws ServiceException
	{
		attachMapper.createAttachment(attachment);
		return attachment;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteAttachment(long id) throws ServiceException
	{
		attachMapper.deleteAttachment(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteBid(Long bidId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId());

		if(!project.getStatus().equals(Project.Status.NEW))
		{
			throw new ForbiddenOperationException("Unable to delete bid");
		}

		bidMapper.deleteBid(bidId);
	}

	private BigDecimal calculateUserRating(User user)
	{
		BigDecimal rating = BigDecimal.ZERO;
		List<Feedback> feedbacks = feedbackMapper.getFeedbacksByUserId(user.getId());
		for (Feedback feedback : feedbacks)
		{
			rating = rating.add(feedback.getMark());
		}
		return rating.divide(new BigDecimal(feedbacks.size()));
	}
}
