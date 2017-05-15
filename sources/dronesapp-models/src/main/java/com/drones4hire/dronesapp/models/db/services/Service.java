package com.drones4hire.dronesapp.models.db.services;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Service extends AbstractEntity
{
	private static final long serialVersionUID = 1513800214237682388L;

	private String name;
	private ServiceCategory category;
	
	public Service()
	{
	}
	
	public Service(long id)
	{
		this.setId(id);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ServiceCategory getCategory()
	{
		return category;
	}

	public void setCategory(ServiceCategory category)
	{
		this.category = category;
	}
}