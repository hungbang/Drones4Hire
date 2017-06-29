package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Project.Status;

import java.util.Date;

public class ProjectStatisticsResult
{

	private Integer count;
	private Status status;
	private Date createdAt;

	public ProjectStatisticsResult()
	{
	}

	public ProjectStatisticsResult(Integer count, Status status)
	{
		this.count = count;
		this.status = status;
	}

	public Integer getCount()
	{
		return count;
	}

	public void setCount(Integer count)
	{
		this.count = count;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
}
