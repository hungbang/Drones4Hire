package com.drones4hire.dronesapp.models.db.commons;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Country extends AbstractEntity
{
	private static final long serialVersionUID = -2091202396207453304L;

	private String name;
	private String code;
	private boolean licenseRequired;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public boolean isLicenseRequired()
	{
		return licenseRequired;
	}

	public void setLicenseRequired(boolean licenseRequired)
	{
		this.licenseRequired = licenseRequired;
	}
}
