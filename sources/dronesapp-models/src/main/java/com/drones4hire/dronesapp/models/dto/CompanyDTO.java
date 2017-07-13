package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.State;

public class CompanyDTO extends AbstractDTO
{
	private static final long serialVersionUID = -7697101906536815484L;

	@NotNull(message = "Name required")
	private String name;
	
	private String webURL;
	
	@NotNull(message = "Contact name required")
	private String contactName;
	
	@NotNull(message = "Contact email required")
	private String contactEmail;
	
	@NotNull(message = "Country required")
	private Country country;

	private State state;

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

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}
}
