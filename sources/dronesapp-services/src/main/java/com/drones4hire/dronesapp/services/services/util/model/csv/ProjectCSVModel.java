package com.drones4hire.dronesapp.services.services.util.model.csv;

import com.drones4hire.dronesapp.models.db.projects.Project;

import java.util.Date;

public class ProjectCSVModel extends AbstractCSVModel
{

	private static final long serialVersionUID = 2288299074656057680L;

	private String title;
	private String clientEmail;
	private String pilotEmail;
	private String duration;
	private String budget;
	private Project.Status status;
	private String country;
	private String city;
	private String service;
	private Date startDate;
	private Date finishDate;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getClientEmail()
	{
		return clientEmail;
	}

	public void setClientEmail(String clientEmail)
	{
		this.clientEmail = clientEmail;
	}

	public String getPilotEmail()
	{
		return pilotEmail;
	}

	public void setPilotEmail(String pilotEmail)
	{
		this.pilotEmail = pilotEmail;
	}

	public String getDuration()
	{
		return duration;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}

	public String getBudget()
	{
		return budget;
	}

	public void setBudget(String budget)
	{
		this.budget = budget;
	}

	public Project.Status getStatus()
	{
		return status;
	}

	public void setStatus(Project.Status status)
	{
		this.status = status;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getService()
	{
		return service;
	}

	public void setService(String service)
	{
		this.service = service;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getFinishDate()
	{
		return finishDate;
	}

	public void setFinishDate(Date finishDate)
	{
		this.finishDate = finishDate;
	}
}
