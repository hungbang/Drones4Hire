package com.drones4hire.dronesapp.models.db.commons;

import java.io.Serializable;

public class Coordinates implements Serializable
{
	private static final long serialVersionUID = 3633519390633868041L;
	
	private double latitude;
	private double longitude;

	public Coordinates()
	{
	}

	public Coordinates(double latitude, double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

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