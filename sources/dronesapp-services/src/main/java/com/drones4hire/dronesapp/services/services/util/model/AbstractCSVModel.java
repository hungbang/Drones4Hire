package com.drones4hire.dronesapp.services.services.util.model;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractCSVModel implements Serializable
{

	private static final long serialVersionUID = 2834201041151306112L;

	private Long id;
	private Date createdAt;
	private Date modifiedAt;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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
