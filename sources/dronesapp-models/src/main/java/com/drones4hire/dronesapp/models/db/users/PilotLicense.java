package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class PilotLicense extends AbstractEntity
{
	private static final long serialVersionUID = -3845232480700290476L;

	private Long userId;
	private String licenseURL;
	private String insuranceURL;
	private Boolean verified;
	
	public PilotLicense()
	{
	}
	
	public PilotLicense(Long userId)
	{
		this.userId = userId;
	}

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

	public Boolean isVerified()
	{
		return verified;
	}

	public void setVerified(Boolean verified)
	{
		this.verified = verified;
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
