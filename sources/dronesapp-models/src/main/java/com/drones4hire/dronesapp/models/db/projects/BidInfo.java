package com.drones4hire.dronesapp.models.db.projects;

import com.drones4hire.dronesapp.models.db.commons.Currency;

import java.util.Date;

public class BidInfo
{

	private Long projectId;
	private String projectTitle;
	private Integer bidsCount;
	private Double bidsAvg;
	private Currency currency;
	private Date endDate;
	private Date createdAt;
	private Date modifiedAt;

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public String getProjectTitle()
	{
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle)
	{
		this.projectTitle = projectTitle;
	}

	public Integer getBidsCount()
	{
		return bidsCount;
	}

	public void setBidsCount(Integer bidsCount)
	{
		this.bidsCount = bidsCount;
	}

	public Double getBidsAvg()
	{
		return bidsAvg;
	}

	public void setBidsAvg(Double bidsAvg)
	{
		this.bidsAvg = bidsAvg;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Date getModifiedAt()
	{
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt)
	{
		this.modifiedAt = modifiedAt;
	}
}
