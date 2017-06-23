package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.users.Group.Role;

public class UserSearchCriteria extends SearchCriteria
{
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private Integer flightHours;
	private Boolean confirmed;
	private Boolean enabled;
	private Role role;
	
	public UserSearchCriteria()
	{
	}
	
	public UserSearchCriteria(Role role)
	{
		super();
		this.role = role;
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

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

	public Integer getFlightHours()
	{
		return flightHours;
	}

	public void setFlightHours(Integer flightHours)
	{
		this.flightHours = flightHours;
	}

	public Boolean getConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed)
	{
		this.confirmed = confirmed;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}
}