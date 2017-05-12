package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.Comment;

public interface CommentMapper
{
	void createComment(Comment comment);

	Comment getCommentById(long id);

	List<Comment> getCommentsByProjectId(Long projectId);

	void updateComment(Comment comment);

	void deleteComment(long id);
}
