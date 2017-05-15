package com.drones4hire.dronesapp.models.db.commons;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Duration extends AbstractEntity
{
	private static final long serialVersionUID = -8574854731176821945L;

	private String title;
	private Double min;
	private Double max;
	private Integer order;
	
	public Duration()
	{
	}
	
	public Duration(long id)
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

	public Double getMin()
	{
		return min;
	}

	public void setMin(Double min)
	{
		this.min = min;
	}

	public Double getMax()
	{
		return max;
	}

	public void setMax(Double max)
	{
		this.max = max;
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
