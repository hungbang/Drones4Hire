package com.drones4hire.dronesapp.models.db.commons;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Location extends AbstractEntity
{
	private static final long serialVersionUID = 1001654360765711983L;

	private String address;
	private Coordinates coordinates;
	private Country country;
	private State state;
	private String city;
	private Integer postcode;
	
	public Location()
	{
	}
	
	public Location(long id)
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