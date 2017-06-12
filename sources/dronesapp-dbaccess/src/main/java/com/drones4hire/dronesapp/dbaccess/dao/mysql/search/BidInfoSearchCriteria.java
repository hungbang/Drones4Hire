package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BidInfoSearchCriteria extends SearchCriteria
{
	@JsonIgnore
	private Long clientId;

	@JsonIgnore
	private Long pilotId;

	private Project.Status status;

	private String title;

	public Long getClientId()
	{
		return clientId;
	}

	public void setClientId(Long clientId)
	{
		this.clientId = clientId;
	}

	public Long getPilotId()
	{
		return pilotId;
	}

	public void setPilotId(Long pilotId)
	{
		this.pilotId = pilotId;
	}

	public Project.Status getStatus()
	{
		return status;
	}

	public void setStatus(Project.Status status)
	{
		this.status = status;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
}
