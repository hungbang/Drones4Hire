package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Location;

import javax.validation.constraints.NotNull;

public class AccountDTO extends AbstractDTO
{
	private static final long serialVersionUID = -5542006534130010886L;

	@NotNull(message = "First name shouldn't be null")
	private String firstName;
	@NotNull(message = "Last name shouldn't be null")
	private String lastName;
	@NotNull(message = "Location shouldn't be null")
	private Location location;
	@NotNull(message = "Photo URL shouldn't be null")
	private String photoURL;
	private String introduction;
	private String summary;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public String getPhotoURL()
	{
		return photoURL;
	}

	public void setPhotoURL(String photoURL)
	{
		this.photoURL = photoURL;
	}

	public String getIntroduction()
	{
		return introduction;
	}

	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}
}
