package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

public class PilotLicenseDTO extends AbstractDTO
{
	private static final long serialVersionUID = -4783150607563567638L;
	
	@NotNull(message = "License url shouldn't be null")
	@URL(message = "Wrong license url")
	private String licenseURL;
	@NotNull(message = "Insurance url shouldn't be null")
	@URL(message = "Wrong insurance url")
	private String insuranceURL;
	@NotNull(message="Verified flag shouldn't be null")
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
	public Boolean isVerified()
	{
		return verified;
	}
	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}
}