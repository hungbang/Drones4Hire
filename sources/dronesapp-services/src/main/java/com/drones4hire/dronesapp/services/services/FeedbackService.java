package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.FeedbackMapper;
import com.drones4hire.dronesapp.models.db.projects.Feedback;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
		List<Feedback> feedbacks = feedbackMapper.getFeedbacksByProjectId(project.getId());
		if (!feedback.getFromUser().getId().equals(project.getClientId()) || !feedback.getToUser().getId()
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
	public Feedback updateFeedback(Feedback feedback) throws ServiceException
	{
		feedbackMapper.updateFeedback(feedback);
		User toUser = userService.getUserById(feedback.getToUser().getId());
		toUser.setRating(calculateUserRating(toUser).doubleValue());
		userService.updateUser(toUser);
		return feedback;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteFeedback(long id) throws ServiceException
	{
		Feedback feedback = getFeedbackById(id);
		feedbackMapper.deleteFeedback(id);
		User toUser = userService.getUserById(feedback.getToUser().getId());
		toUser.setRating(calculateUserRating(toUser).doubleValue());
		userService.updateUser(toUser);
	}

	private BigDecimal calculateUserRating(User user)
	{
		BigDecimal rating = BigDecimal.ZERO;
		List<Feedback> feedbacks = getFeedbacksByUserId(user.getId());
		for (Feedback feedback : feedbacks)
		{
			rating = rating.add(feedback.getMark());
		}
		return rating.divide(new BigDecimal(feedbacks.size()));
	}
}
