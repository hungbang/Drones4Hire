package com.drones4hire.dronesapp.models.dto;

import java.io.Serializable;

public class AbstractDTO implements Serializable
{
	private static final long serialVersionUID = 8699103582566439994L;

	private Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
