package com.drones4hire.dronesapp.models.db.projects;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Feedback extends AbstractEntity
{
	private static final long serialVersionUID = 1985019619730337149L;

	private Long fromUserId;
	private Long toUserId;
	private Long projectId;
	private BigDecimal mark;
	private String comment;

	public Long getFromUserId()
	{
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId)
	{
		this.fromUserId = fromUserId;
	}

	public Long getToUserId()
	{
		return toUserId;
	}

	public void setToUserId(Long toUserId)
	{
		this.toUserId = toUserId;
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
