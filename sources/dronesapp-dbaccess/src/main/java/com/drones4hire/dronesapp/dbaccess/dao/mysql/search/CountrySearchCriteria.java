package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

public class CountrySearchCriteria extends SearchCriteria
{

	private String name;
	private String code;
	private Boolean licenseRequired;

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

	public Boolean getLicenseRequired()
	{
		return licenseRequired;
	}

	public void setLicenseRequired(Boolean licenseRequired)
	{
		this.licenseRequired = licenseRequired;
	}
}
