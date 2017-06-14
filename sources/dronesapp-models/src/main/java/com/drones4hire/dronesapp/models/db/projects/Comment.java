package com.drones4hire.dronesapp.models.db.projects;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.users.User;

public class Comment extends AbstractEntity
{
	private static final long serialVersionUID = -1084490456033391101L;

	private User user;
	private Long projectId;
	private String comment;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
