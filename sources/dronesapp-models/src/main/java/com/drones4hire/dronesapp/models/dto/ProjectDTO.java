package com.drones4hire.dronesapp.models.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.services.Service;

public class ProjectDTO extends AbstractDTO
{

	private static final long serialVersionUID = 4920176532424502764L;

	@NotNull(message = "Title required")
	private String title;

	private String summary;

	private Long bidId;

	private Long clientId;

	private Long pilotId;

	@NotNull(message = "Service required")
	private Service service;

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

	private Date awardDate;

	@NotNull(message = "Status required")
	private Project.Status status;

	private List<PaidOptionDTO> paidOptions;

	private List<Attachment> attachments;
	
	private String paymentMethod;
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Long getBidId()
	{
		return bidId;
	}

	public void setBidId(Long bidId)
	{
		this.bidId = bidId;
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

	public Date getAwardDate()
	{
		return awardDate;
	}

	public void setAwardDate(Date awardDate)
	{
		this.awardDate = awardDate;
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
	
	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
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
	
	@AssertTrue(message = "Payment method not specified")
	public boolean isPaymentMethodNotNull()
	{
		return paidOptions != null ? paymentMethod != null : true;
	}
}
