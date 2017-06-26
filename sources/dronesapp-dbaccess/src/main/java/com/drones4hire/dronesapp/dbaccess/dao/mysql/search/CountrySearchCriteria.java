package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

public class CountrySearchCriteria extends SearchCriteria
{

	private String name;
	private Boolean licenseRequired;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Boolean isLicenseRequired()
	{
		return licenseRequired;
	}

	public void setLicenseRequired(Boolean licenseRequired)
	{
		this.licenseRequired = licenseRequired;
	}
}
