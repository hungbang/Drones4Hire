package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

public class CoordinatesDTO
{

	@NotNull(message = "Latitude required")
	private double latitude;

	@NotNull(message = "Longitude required")
	private double longitude;

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
}
