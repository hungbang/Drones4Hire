package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.services.Service;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ProjectDTO extends AbstractDTO
{

	private static final long serialVersionUID = 4920176532424502764L;

	@NotNull(message = "Title required")
	private String title;

	private String summary;

	private Long bidId;

	@NotNull(message = "Service required")
	private Service service;

	@NotNull(message = "Duration required")
	private DurationDTO duration;

	@NotNull(message = "Location required")
	private LocationDTO location;

	@NotNull(message = "Budget required")
	private BudgetDTO budget;

	@NotNull(message = "Post production required")
	private Boolean postProductionRequired;

	@NotNull(message = "Start date required")
	private Date startDate;

	private Date finishDate;

	@NotNull(message = "Status required")
	private Project.Status status;

	private List<PaidOptionDTO> paidOptions;

	private List<Attachment> attachments;
	
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

	public Service getService()
	{
		return service;
	}

	public void setService(Service service)
	{
		this.service = service;
	}

	public DurationDTO getDuration()
	{
		return duration;
	}

	public void setDuration(DurationDTO duration)
	{
		this.duration = duration;
	}

	public LocationDTO getLocation()
	{
		return location;
	}

	public void setLocation(LocationDTO location)
	{
		this.location = location;
	}

	public Long getBidId()
	{
		return bidId;
	}

	public void setBidId(Long bidId)
	{
		this.bidId = bidId;
	}

	public BudgetDTO getBudget()
	{
		return budget;
	}

	public void setBudget(BudgetDTO budget)
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

	public Project.Status getStatus()
	{
		return status;
	}

	public void setStatus(Project.Status status)
	{
		this.status = status;
	}

	public List<PaidOptionDTO> getPaidOptions()
	{
		return paidOptions;
	}

	public void setPaidOptions(List<PaidOptionDTO> paidOptions)
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

	@AssertTrue(message = "Type ENUM confirmation not matching")
	public boolean isConfirmationValid()
	{
		try
		{
			Project.Status.valueOf(status.name());
			return true;
		} catch (IllegalArgumentException e)
		{
			return false;
		}
	}
}
