package com.drones4hire.dronesapp.models.db.projects;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.commons.Currency;

public class BidInfo
{

	private Long projectId;
	private Integer bidsCount;
	private Double bidsAvg;
	private BigDecimal bidsMax;
	private Currency currency;

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
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

	public BigDecimal getBidsMax()
	{
		return bidsMax;
	}

	public void setBidsMax(BigDecimal bidsMax)
	{
		this.bidsMax = bidsMax;
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
