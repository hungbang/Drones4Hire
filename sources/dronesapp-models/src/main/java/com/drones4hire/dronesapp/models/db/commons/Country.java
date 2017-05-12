package com.drones4hire.dronesapp.models.db.commons;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Country extends AbstractEntity
{
	private static final long serialVersionUID = -2091202396207453304L;

	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
