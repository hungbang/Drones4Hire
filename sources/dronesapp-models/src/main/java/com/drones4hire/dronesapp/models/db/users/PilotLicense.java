package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class PilotLicense extends AbstractEntity
{
	private static final long serialVersionUID = -3845232480700290476L;

	private Long pilotId;
	private String licenseURL;
	private String insuranceURL;
	private Boolean verified;

	public String getLicenseURL()
	{
		return licenseURL;
	}

	public void setLicenseURL(String licenseURL)
	{
		this.licenseURL = licenseURL;
	}

	public String getInsuranceURL()
	{
		return insuranceURL;
	}

	public void setInsuranceURL(String insuranceURL)
	{
		this.insuranceURL = insuranceURL;
	}

	public Boolean getVerified()
	{
		return verified;
	}

	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}

	public Long getPilotId()
	{
		return pilotId;
	}

	public void setPilotId(Long pilotId)
	{
		this.pilotId = pilotId;
	}
}
