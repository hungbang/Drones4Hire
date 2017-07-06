package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class FeedbackDTO extends AbstractDTO
{

	private static final long serialVersionUID = -8537747656569535467L;

	@NotNull(message = "From user ID required")
	private AccountDTO fromUser;

	@NotNull(message = "To user ID required")
	private AccountDTO toUser;

	@NotNull(message = "Project ID required")
	private Long projectId;

	@NotNull(message = "Mark required")
	@Min(value = 0, message = "Mark need to be positive")
	@Max(value = 5, message = "Mark need to be less than 5")
	private BigDecimal mark;

	private String comment;

	public AccountDTO getFromUser()
	{
		return fromUser;
	}

	public void setFromUser(AccountDTO fromUser)
	{
		this.fromUser = fromUser;
	}

	public AccountDTO getToUser()
	{
		return toUser;
	}

	public void setToUser(AccountDTO toUser)
	{
		this.toUser = toUser;
	}

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public BigDecimal getMark()
	{
		return mark;
	}

	public void setMark(BigDecimal mark)
	{
		this.mark = mark;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
