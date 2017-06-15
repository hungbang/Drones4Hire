package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Project.Status;

import java.util.List;

public class ProjectSearchCriteria extends SearchCriteria
{

	private String title;
	private long clientId;
	private long pilotId;
	private long serviceCategoryId;
	private long durationId;
	private long locationId;
	private Integer postcode;
	private long budgetId;
	private List<Status> statuses;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getClientId()
	{
		return clientId;
	}

	public void setClientId(long clientId)
	{
		this.clientId = clientId;
	}

	public long getPilotId()
	{
		return pilotId;
	}

	public void setPilotId(long pilotId)
	{
		this.pilotId = pilotId;
	}

	public long getServiceCategoryId()
	{
		return serviceCategoryId;
	}

	public void setServiceCategoryId(long serviceCategoryId)
	{
		this.serviceCategoryId = serviceCategoryId;
	}

	public long getDurationId()
	{
		return durationId;
	}

	public void setDurationId(long durationId)
	{
		this.durationId = durationId;
	}

	public long getLocationId()
	{
		return locationId;
	}

	public void setLocationId(long locationId)
	{
		this.locationId = locationId;
	}

	public Integer getPostcode()
	{
		return postcode;
	}

	public void setPostcode(Integer postcode)
	{
		this.postcode = postcode;
	}

	public long getBudgetId()
	{
		return budgetId;
	}

	public void setBudgetId(long budgetId)
	{
		this.budgetId = budgetId;
	}

	public List<Status> getStatus()
	{
		return statuses;
	}

	public void setStatus(List<Status> statuses)
	{
		this.statuses = statuses;
	}
}
