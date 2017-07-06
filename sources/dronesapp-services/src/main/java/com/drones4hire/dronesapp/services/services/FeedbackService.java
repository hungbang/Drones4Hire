package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.FeedbackMapper;
import com.drones4hire.dronesapp.models.db.projects.Feedback;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService
{

	@Autowired
	private FeedbackMapper feedbackMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Transactional(rollbackFor = Exception.class)
	public Feedback createFeedback(Feedback feedback) throws ServiceException
	{
		Project project = projectService.getProjectById(feedback.getProjectId());
		User user = userService.getUserById(feedback.getFromUser().getId());
		if((!feedback.getFromUser().getId().equals(project.getClientId()) || !feedback.getToUser().getId().equals(project.getPilotId())
				|| !project.getStatus().equals(Project.Status.COMPLETED) || feedbackMapper.getFeedbacksByProjectId(project.getId()) != null)
				&& !user.getRoles().contains(Group.Role.ROLE_ADMIN))
		{
			throw new ForbiddenOperationException();
		}
		feedbackMapper.createFeedback(feedback);
		return feedback;
	}

	@Transactional(readOnly = true)
	public Feedback getFeedbackById(long id)
	{
		return feedbackMapper.getFeedbackById(id);
	}

	@Transactional(readOnly = true)
	public List<Feedback> getFeedbacksByProjectId(long id)
	{
		return feedbackMapper.getFeedbacksByProjectId(id);
	}

	@Transactional(readOnly = true)
	public List<Feedback> getFeedbacksByUserId(long id)
	{
		return feedbackMapper.getFeedbacksByUserId(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Feedback updateFeedback(Feedback feedback)
	{
		feedbackMapper.updateFeedback(feedback);
		return feedback;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteFeedback(long id)
	{
		feedbackMapper.deleteFeedback(id);
	}
}
