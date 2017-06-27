package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.commons.Coordinates;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.State;

public class LocationDTO extends AbstractDTO
{
	private static final long serialVersionUID = 2949971763043720272L;
	
	@NotNull(message="Address required")
	private String address;
	
	private Coordinates coordinates;
	
	@NotNull(message="Country required")
	private Country country;
	
	private State state;
	
	@NotNull(message="City required")
	private String city;

	private Integer postcode;
	
	public LocationDTO()
	{
	}
	
	public LocationDTO(Long id)
	{
		this.setId(id);
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Coordinates getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates)
	{
		this.coordinates = coordinates;
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

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public Integer getPostcode()
	{
		return postcode;
	}

	public void setPostcode(Integer postcode)
	{
		this.postcode = postcode;
	}
}