package com.drones4hire.dronesapp.models.db.projects;

import java.util.Date;
import java.util.List;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.services.Service;

public class Project extends AbstractEntity
{
	private static final long serialVersionUID = 7209775795910871836L;

	public enum Status
	{
		NEW, IN_PROGRESS, COMPLETED, CANCELLED, PENDING
	}

	private String title;
	private String summary;
	private Long clientId;
	private Long pilotId;
	private Service service;
	private Duration duration;
	private Location location;
	private Budget budget;
	private Boolean postProductionRequired;
	private Date startDate;
	private Date finishDate;
	private Date awardDate;
	private Status status;
	private List<PaidOption> paidOptions;
	private List<Attachment> attachments;

	public Service getService()
	{
		return service;
	}

	public void setService(Service service)
	{
		this.service = service;
	}

	public Duration getDuration()
	{
		return duration;
	}

	public void setDuration(Duration duration)
	{
		this.duration = duration;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

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

	public Budget getBudget()
	{
		return budget;
	}

	public void setBudget(Budget budget)
	{
		this.budget = budget;
	}

	public Boolean getPostProductionRequired()
	{
		return postProductionRequired;
	}

	public void setPostProductionRequired(Boolean postProductionRequired)
	{
		this.postProductionRequired = postProductionRequired;
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

	public Date getAwardDate()
	{
		return awardDate;
	}

	public void setAwardDate(Date awardDate)
	{
		this.awardDate = awardDate;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public List<PaidOption> getPaidOptions()
	{
		return paidOptions;
	}

	public void setPaidOptions(List<PaidOption> paidOptions)
	{
		this.paidOptions = paidOptions;
	}

	public List<Attachment> getAttachments()
	{
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments)
	{
		this.attachments = attachments;
	}
}