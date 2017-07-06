package com.drones4hire.dronesapp.models.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.users.Group;

public class AccountDTO extends AbstractDTO
{
	private static final long serialVersionUID = -5542006534130010886L;

	private String username;
	
	private String email;
	
	@NotNull(message = "First name required")
	private String firstName;
	
	@NotNull(message = "Last name required")
	private String lastName;
	
	@NotNull(message = "Location required")
	private LocationDTO location;
	
	@NotNull(message = "Photo URL required")
	private String photoURL;
	
	private String introduction;
	
	private String summary;

	private Double rating;

	@Min(value = 0, message = "Flight hours should be positive")
	private Integer flightHours;

	@NotNull(message = "Wallet required")
	private WalletDTO wallet;
	
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

	public LocationDTO getLocation()
	{
		return location;
	}

	public void setLocation(LocationDTO location)
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

	public Double getRating()
	{
		return rating;
	}

	public void setRating(Double rating)
	{
		this.rating = rating;
	}

	public Integer getFlightHours()
	{
		return flightHours;
	}

	public void setFlightHours(Integer flightHours)
	{
		this.flightHours = flightHours;
	}

	public WalletDTO getWallet()
	{
		return wallet;
	}

	public void setWallet(WalletDTO wallet)
	{
		this.wallet = wallet;
	}

	public List<Group> getGroups()
	{
		return groups;
	}

	public void setGroups(List<Group> groups)
	{
		this.groups = groups;
	}
}
