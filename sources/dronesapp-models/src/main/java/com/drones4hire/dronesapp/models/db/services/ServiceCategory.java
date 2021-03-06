package com.drones4hire.dronesapp.models.db.services;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class ServiceCategory extends AbstractEntity
{
	private static final long serialVersionUID = 4731998442694141990L;

	private String name;
	private Integer order;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
