package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.commons.Coordinates;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.dto.CoordinatesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.Date;
import java.util.List;

public class ProjectSearchCriteria extends SearchCriteria
{

	private static final Integer KILOMETER = 6371;

	private static final Integer MILE = 3959;

	private Long id;
	private String title;
	private Long clientId;
	private Long pilotId;
	private String clientEmail;
	private String pilotEmail;
	private String city;
	private Long serviceCategoryId;
	private Long durationId;
	private Long locationId;
	private Integer postcode;
	private Long budgetId;
	private List<Status> statuses;
	private Date createdAtBefore;
	private Date createdAtAfter;
	@Valid
	private CoordinatesDTO topLeftCoordinates;
	@Valid
	private CoordinatesDTO bottomRightCoordinates;
	private Integer range;
	private Integer unit = MILE;
	@JsonIgnore
	private Coordinates userCoordinates;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Long getClientId()
	{
		return clientId;
	}

	public void setClientId(Long clientId)
	{
		this.clientId = clientId;
	}

	public Long getPilotId()
	{
		return pilotId;
	}

	public void setPilotId(Long pilotId)
	{
		this.pilotId = pilotId;
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

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
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

	public Integer getRange()
	{
		return range;
	}

	public void setRange(Integer range)
	{
		this.range = range;
	}

	public Integer getUnit()
	{
		return unit;
	}

	public void setUnit(Integer unit)
	{
		this.unit = unit;
	}

	public Coordinates getUserCoordinates()
	{
		return userCoordinates;
	}

	public void setUserCoordinates(Coordinates userCoordinates)
	{
		this.userCoordinates = userCoordinates;
	}

	@AssertTrue(message = "Coordinates should be have same type pair")
	public boolean isCoordinagtesHavePair()
	{
		return (topLeftCoordinates == null && bottomRightCoordinates == null) || (topLeftCoordinates != null && bottomRightCoordinates != null);
	}
}
