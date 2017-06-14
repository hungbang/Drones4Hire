package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;

import java.util.List;

public class ProjectSearchResult
{
	private Long id;
	private Project project;
	private List<Bid> bids;
	private User client;
	private User pilot;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Project getProject()
	{
		return project;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	public List<Bid> getBids()
	{
		return bids;
	}

	public void setBids(List<Bid> bids)
	{
		this.bids = bids;
	}

	public User getClient()
	{
		return client;
	}

	public void setClient(User client)
	{
		this.client = client;
	}

	public User getPilot()
	{
		return pilot;
	}

	public void setPilot(User pilot)
	{
		this.pilot = pilot;
	}
}
