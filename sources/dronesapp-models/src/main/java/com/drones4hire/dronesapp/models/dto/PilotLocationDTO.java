package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

public class PilotLocationDTO extends AbstractDTO
{

	private static final long serialVersionUID = -786791357816771734L;

	@NotNull(message = "Office required")
	private String office;

	@NotNull(message = "Phone required")
	private String phone;

	private String alternativePhone;

	@NotNull(message = "Location required")
	private LocationDTO location;

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

	public LocationDTO getLocation()
	{
		return location;
	}

	public void setLocation(LocationDTO location)
	{
		this.location = location;
	}
}
