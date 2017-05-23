package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

public class ProjectSearchCriteria extends SearchCriteria
{

	private String title;
	private long clientId;
	private long serviceCategoryId;
	private long durationId;
	private long locationId;
	private long budgetId;

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

	public long getBudgetId()
	{
		return budgetId;
	}

	public void setBudgetId(long budgetId)
	{
		this.budgetId = budgetId;
	}
}
