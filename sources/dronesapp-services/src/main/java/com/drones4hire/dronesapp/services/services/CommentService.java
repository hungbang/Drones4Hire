package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CommentMapper;
import com.drones4hire.dronesapp.models.db.projects.Comment;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.drones4hire.dronesapp.models.db.projects.Project.Status.NEW;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

@Service
public class CommentService
{

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Transactional(rollbackFor = Exception.class)
	public Comment createComment(Comment comment, long principalId) throws ServiceException
	{
		checkAuthorities(comment.getProjectId(), principalId);
		commentMapper.createComment(comment);
		return comment;
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
		return commentMapper.getCommentsByProjectId(projectId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Comment updateComment(Comment comment)
	{
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
		User user = userService.getUserById(principalId);
		Project project = projectService.getProjectById(projectId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			if (principalId != project.getClientId())
			{
				throw new ForbiddenOperationException();
			}
		} else if (!project.getStatus().equals(NEW) && user.getRoles().contains(ROLE_PILOT))
		{
			if (principalId != project.getClientId())
			{
				throw new ForbiddenOperationException();
			}
		}
	}
}
