package com.drones4hire.dronesapp.models.db.projects;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Comment extends AbstractEntity
{
	private static final long serialVersionUID = -1084490456033391101L;

	private Long userId;
	private Long projectId;
	private String comment;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
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
