package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Country;

import javax.validation.constraints.NotNull;

public class CompanyDTO extends AbstractDTO
{

	private static final long serialVersionUID = -7697101906536815484L;

	@NotNull(message = "Name shouldn't be null")
	private String name;
	private String webURL;
	@NotNull(message = "Contact name shouldn't be null")
	private String contactName;
	@NotNull(message = "Contact email shouldn't be null")
	private String contactEmail;
	@NotNull(message = "Country shouldn't be null")
	private Country country;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getWebURL()
	{
		return webURL;
	}

	public void setWebURL(String webURL)
	{
		this.webURL = webURL;
	}

	public String getContactName()
	{
		return contactName;
	}

	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}

	public String getContactEmail()
	{
		return contactEmail;
	}

	public void setContactEmail(String contactEmail)
	{
		this.contactEmail = contactEmail;
	}

	public Country getCountry()
	{
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}
}
