package com.drones4hire.dronesapp.models.db.portfolio;

import java.util.List;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;

public class PortfolioItem extends AbstractEntity
{
	private static final long serialVersionUID = -2051191606069736283L;

	public enum Type
	{
		PHOTO, VIDEO
	}

	private Long pilotId;
	private String name;
	private String title;
	private String summary;
	private Type type;
	private String itemURL;
	private List<ServiceCategory> serviceCategories;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public String getItemURL()
	{
		return itemURL;
	}

	public void setItemURL(String itemURL)
	{
		this.itemURL = itemURL;
	}

	public List<ServiceCategory> getServiceCategories()
	{
		return serviceCategories;
	}

	public void setServiceCategories(List<ServiceCategory> serviceCategories)
	{
		this.serviceCategories = serviceCategories;
	}

	public Long getPilotId()
	{
		return pilotId;
	}

	public void setPilotId(Long pilotId)
	{
		this.pilotId = pilotId;
	}
}
