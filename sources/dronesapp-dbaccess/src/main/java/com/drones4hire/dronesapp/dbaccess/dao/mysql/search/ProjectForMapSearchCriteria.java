package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.dto.CoordinatesDTO;

import java.util.Date;

public class ProjectForMapSearchCriteria
{
	private Long id;
	private Long budgetId;
	private String title;
	private Status status;
	@NotNull(message = "Top left bounding box coordinates required")
	@Valid
	private CoordinatesDTO topLeftCoordinates;
	@NotNull(message = "Bottom right bounding box coordinates required")
	@Valid
	private CoordinatesDTO bottomRightCoordinates;
	private Date createdAtBefore;
	private Date createdAtAfter;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getBudgetId()
	{
		return budgetId;
	}

	public void setBudgetId(Long budgetId)
	{
		this.budgetId = budgetId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public CoordinatesDTO getTopLeftCoordinates()
	{
		return topLeftCoordinates;
	}

	public void setTopLeftCoordinates(CoordinatesDTO topLeftCoordinates)
	{
		this.topLeftCoordinates = topLeftCoordinates;
	}

	public CoordinatesDTO getBottomRightCoordinates()
	{
		return bottomRightCoordinates;
	}

	public void setBottomRightCoordinates(CoordinatesDTO bottomRightCoordinates)
	{
		this.bottomRightCoordinates = bottomRightCoordinates;
	}

	public Date getCreatedAtBefore()
	{
		return createdAtBefore;
	}

	public void setCreatedAtBefore(Date createdAtBefore)
	{
		this.createdAtBefore = createdAtBefore;
	}

	public Date getCreatedAtAfter()
	{
		return createdAtAfter;
	}

	public void setCreatedAtAfter(Date createdAtAfter)
	{
		this.createdAtAfter = createdAtAfter;
	}
}
