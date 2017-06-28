package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.commons.Coordinates;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;

public class ProjectForMapContext extends SearchResult
{

	private Long id;
	private Long budgetId;
	private String title;
	private Status status;
	private Coordinates coordinates;

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

	public Coordinates getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates)
	{
		this.coordinates = coordinates;
	}
}
