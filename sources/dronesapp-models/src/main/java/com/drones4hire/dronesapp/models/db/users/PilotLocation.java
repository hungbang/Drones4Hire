package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Location;

public class PilotLocation extends AbstractEntity
{
	private static final long serialVersionUID = -205989245888562044L;

	private Long userId;
	private String office;
	private String phone;
	private String alternativePhone;
	private Location location;

	public String getOffice()
	{
		return office;
	}

	public void setOffice(String office)
	{
		this.office = office;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getAlternativePhone()
	{
		return alternativePhone;
	}

	public void setAlternativePhone(String alternativePhone)
	{
		this.alternativePhone = alternativePhone;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
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
