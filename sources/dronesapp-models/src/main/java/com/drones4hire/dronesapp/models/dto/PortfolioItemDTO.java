package com.drones4hire.dronesapp.models.dto;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem.Type;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;

public class PortfolioItemDTO extends AbstractDTO
{
	private static final long serialVersionUID = -2051191606069736283L;

	@NotNull(message = "Name shouldn't be null")
	private String name;
	@NotNull(message = "Title shouldn't be null")
	private String title;
	@NotNull(message = "Summary shouldn't be null")
	private String summary;
	@NotNull(message = "Type shouldn't be null")
	private Type type;
	@NotNull(message = "Item Url shouldn't be null")
	private String itemURL;
	@NotNull(message = "Categories shouldn't be null")
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

	@AssertTrue(message = "Type ENUM confirmation not matching")
	public boolean isConfirmationValid()
	{
		try
		{
			Type.valueOf(type.name());
			return true;
		} catch (IllegalArgumentException e)
		{
			return false;
		}
	}
}