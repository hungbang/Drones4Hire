package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.dto.CoordinatesDTO;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.Date;
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
	private Date createdAtBefore;
	private Date createdAtAfter;
	@Valid
	private CoordinatesDTO topLeftCoordinates;
	@Valid
	private CoordinatesDTO bottomRightCoordinates;

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

	public List<Status> getStatuses()
	{
		return statuses;
	}

	public void setStatuses(List<Status> statuses)
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

	@AssertTrue(message = "Coordinates should be have same type pair")
	public boolean isCoordinagtesHavePair()
	{
		return (topLeftCoordinates == null && bottomRightCoordinates == null) || (topLeftCoordinates != null && bottomRightCoordinates != null);
	}
}
