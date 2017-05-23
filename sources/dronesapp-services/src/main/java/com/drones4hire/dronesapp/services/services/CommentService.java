package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CommentMapper;
import com.drones4hire.dronesapp.models.db.projects.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService
{

	@Autowired
	private CommentMapper commentMapper;

	@Transactional(rollbackFor = Exception.class)
	public Comment createComment(Comment comment)
	{
		commentMapper.createComment(comment);
		return comment;
	}

	@Transactional(readOnly = true)
	public Comment getCommentById(long id)
	{
		return commentMapper.getCommentById(id);
	}

	@Transactional(readOnly = true)
	public List<Comment> getCommentsByProjectId(long projectId)
	{
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
}
