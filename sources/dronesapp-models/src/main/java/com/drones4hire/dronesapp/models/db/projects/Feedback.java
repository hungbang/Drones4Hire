package com.drones4hire.dronesapp.models.db.projects;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.users.User;

public class Feedback extends AbstractEntity
{
	private static final long serialVersionUID = 1985019619730337149L;

	private User fromUser;
	private User toUser;
	private Long projectId;
	private BigDecimal mark;
	private String comment;

	public User getFromUser()
	{
		return fromUser;
	}

	public void setFromUser(User fromUser)
	{
		this.fromUser = fromUser;
	}

	public User getToUser()
	{
		return toUser;
	}

	public void setToUser(User toUser)
	{
		this.toUser = toUser;
	}

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public BigDecimal getMark()
	{
		return mark;
	}

	public void setMark(BigDecimal mark)
	{
		this.mark = mark;
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
