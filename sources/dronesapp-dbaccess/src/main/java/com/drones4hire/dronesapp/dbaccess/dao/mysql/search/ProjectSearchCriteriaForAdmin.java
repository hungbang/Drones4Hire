package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Project;

import java.util.List;

public class ProjectSearchCriteriaForAdmin extends SearchCriteria
{

	private String title;
	private String clientEmail;
	private String pilotEmail;
	private long serviceCategoryId;
	private long durationId;
	private long locationId;
	private String city;
	private Integer postcode;
	private long budgetId;
	private List<Project.Status> statuses;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getClientEmail()
	{
		return clientEmail;
	}

	public void setClientEmail(String clientEmail)
	{
		this.clientEmail = clientEmail;
	}

	public String getPilotEmail()
	{
		return pilotEmail;
	}

	public void setPilotEmail(String pilotEmail)
	{
		this.pilotEmail = pilotEmail;
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

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
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

	public List<Project.Status> getStatuses()
	{
		return statuses;
	}

	public void setStatuses(List<Project.Status> statuses)
	{
		this.statuses = statuses;
	}
}
