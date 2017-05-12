package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Country;

public class Company extends AbstractEntity
{
	private static final long serialVersionUID = -7154264953434031300L;

	private Long userId;
	private String name;
	private String webURL;
	private String contactName;
	private String contactEmail;
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

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
}
