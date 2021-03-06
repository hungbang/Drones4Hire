package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CommentMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.projects.Comment;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

@Service
public class CommentService
{

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AWSEmailService emailService;

	@Transactional(rollbackFor = Exception.class)
	public Comment createComment(Comment comment, long principalId) throws ServiceException
	{
		checkAuthorities(comment.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		comment.setUser(user);
		commentMapper.createComment(comment);
		Project project = projectMapper.getProjectById(comment.getProjectId());
		if(!project.getClientId().equals(principalId))
		{
			emailService.sendNewCommentReceiveEmail(project);
		}
		return commentMapper.getCommentById(comment.getId());
	}

	@Transactional(readOnly = true)
	public Comment getCommentById(long id)
	{
		return commentMapper.getCommentById(id);
	}

	@Transactional(readOnly = true)
	public List<Comment> getCommentsByProjectId(long projectId, long principalId) throws ServiceException
	{
		checkAuthorities(projectId, principalId);
		Project project = projectMapper.getProjectById(projectId);
		List<Comment> comments = commentMapper.getCommentsByProjectId(projectId);
		for(PaidOption option : project.getPaidOptions()) {
			if(option.getRating().equals(ProjectService.PRIVATE_PAID_OPTION)) {
				for(Comment comment : comments) {
					if(project.getClientId().equals(comment.getUser().getId())) {
						comment.setUser(null);
					}
				}
			}
		}
		return comments;
	}

	@Transactional(rollbackFor = Exception.class)
	public Comment updateComment(Comment comment, long principalId) throws ServiceException
	{
		checkAuthorities(comment.getProjectId(), principalId);
		commentMapper.updateComment(comment);
		return comment;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteComment(long id)
	{
		commentMapper.deleteComment(id);
	}
	
	private void checkAuthorities(long projectId, long principalId) throws ServiceException
	{
		Project project = projectMapper.getProjectById(projectId);
		switch (project.getStatus())
		{
		case NEW:
		case PENDING:
			return;
		default:
			if (principalId != project.getClientId() && principalId != project.getPilotId())
			{
				throw new ForbiddenOperationException();
			}
		}
	}
}
