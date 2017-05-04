package com.drones4hire.dronesapp.models.db;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;

public abstract class AbstractEntity implements Serializable
{
	private static final long serialVersionUID = 6187567312503626298L;

	private Long id;
	@Transient
	private Date modifiedAt;
	@Transient
	private Date createdAt;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getModifiedAt()
	{
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt)
	{
		this.modifiedAt = modifiedAt;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
}