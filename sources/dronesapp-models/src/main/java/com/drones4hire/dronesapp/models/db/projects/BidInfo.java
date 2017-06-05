package com.drones4hire.dronesapp.models.db.projects;

import com.drones4hire.dronesapp.models.db.commons.Currency;

public class BidInfo
{

	private Long projectId;
	private String projectTitle;
	private Integer bidsCount;
	private Double bidsAvg;
	private Currency currency;

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
}
