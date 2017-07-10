package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Project;

import java.util.Date;
import java.util.List;

public class ProjectSearchCriteriaForAdmin extends SearchCriteria
{

	private String title;
	private String clientEmail;
	private String pilotEmail;
	private Long serviceCategoryId;
	private Long durationId;
	private Long locationId;
	private String city;
	private Integer postcode;
	private Long budgetId;
	private List<Project.Status> statuses;
	private Date createdAtBefore;
	private Date createdAtAfter;

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

	public Long getServiceCategoryId()
	{
		return serviceCategoryId;
	}

	public void setServiceCategoryId(Long serviceCategoryId)
	{
		this.serviceCategoryId = serviceCategoryId;
	}

	public Long getDurationId()
	{
		return durationId;
	}

	public void setDurationId(Long durationId)
	{
		this.durationId = durationId;
	}

	public Long getLocationId()
	{
		return locationId;
	}

	public void setLocationId(Long locationId)
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

	public Long getBudgetId()
	{
		return budgetId;
	}

	public void setBudgetId(Long budgetId)
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
