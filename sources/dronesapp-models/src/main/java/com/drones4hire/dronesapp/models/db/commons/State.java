package com.drones4hire.dronesapp.models.db.commons;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class State extends AbstractEntity
{
	private static final long serialVersionUID = 3967170236154798084L;

	private String name;
	private String code;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
