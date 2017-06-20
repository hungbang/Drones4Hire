package com.drones4hire.dronesapp.services.services;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CommentMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.projects.Comment;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class CommentService
{

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private UserService userService;

	@Transactional(rollbackFor = Exception.class)
	public Comment createComment(Comment comment, long principalId) throws ServiceException
	{
		checkAuthorities(comment.getProjectId(), principalId);
		comment.setUser(userService.getUserById(principalId));
		commentMapper.createComment(comment);
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
		return commentMapper.getCommentsByProjectId(projectId);
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
		User user = userService.getUserById(principalId);
		if(! user.getRoles().contains(Group.Role.ROLE_ADMIN))
		{
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
}
