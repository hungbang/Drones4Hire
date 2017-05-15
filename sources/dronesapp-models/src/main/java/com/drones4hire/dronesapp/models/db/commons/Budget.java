package com.drones4hire.dronesapp.models.db.commons;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Budget extends AbstractEntity
{
	private static final long serialVersionUID = -6915325435084023500L;

	private String title;
	private BigDecimal min;
	private BigDecimal max;
	private Currency currency;
	private Integer order;
	
	public Budget()
	{
	}
	
	public Budget(long id)
	{
		this.setId(id);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public BigDecimal getMin()
	{
		return min;
	}

	public void setMin(BigDecimal min)
	{
		this.min = min;
	}

	public BigDecimal getMax()
	{
		return max;
	}

	public void setMax(BigDecimal max)
	{
		this.max = max;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public Integer getOrder()
	{
		return order;
	}

	public void setOrder(Integer order)
	{
		this.order = order;
	}
}
