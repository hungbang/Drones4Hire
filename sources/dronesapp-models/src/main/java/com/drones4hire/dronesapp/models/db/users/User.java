package com.drones4hire.dronesapp.models.db.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User extends AbstractEntity
{
	private static final long serialVersionUID = 2720141152633805371L;

	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private String photoURL;
	private String introduction;
	private String summary;
	private Integer flightHours;
	private Boolean confirmed;
	private Boolean enabled;
	private Double rating;
	private Double hourlyRate;
	private Location location;
	private List<Group> groups = new ArrayList<>();

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
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

	public Integer getFlightHours()
	{
		return flightHours;
	}

	public void setFlightHours(Integer flightHours)
	{
		this.flightHours = flightHours;
	}

	public Boolean isConfirmed()
	{
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed)
	{
		this.confirmed = confirmed;
	}

	public Boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public List<Group> getGroups()
	{
		return groups;
	}

	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}

	public Double getRating()
	{
		return rating;
	}

	public void setRating(Double rating)
	{
		this.rating = rating;
	}

	public Double getHourlyRate()
	{
		return hourlyRate;
	}

	public void setHourlyRate(Double hourlyRate)
	{
		this.hourlyRate = hourlyRate;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	@JsonIgnore
	public List<Role> getRoles()
	{
		Set<Role> roles = new HashSet<>();
		for (Group group : groups)
		{
			roles.add(group.getRole());
		}
		return new ArrayList<>(roles);
	}

}
