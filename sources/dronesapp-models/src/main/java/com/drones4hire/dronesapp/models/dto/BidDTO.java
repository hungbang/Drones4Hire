package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BidDTO extends AbstractDTO
{

	private static final long serialVersionUID = -7356654337079186928L;

	@NotNull(message="Project id required")
	private Long projectId;

	private String comment;

	@NotNull(message="Amount required")
	@Min(value = 0, message = "Amount should be positive")
	private BigDecimal amount;

	@NotNull(message="Currency required")
	private Currency currency;

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	@AssertTrue(message = "Currency ENUM confirmation not matching")
	public boolean isConfirmationValid()
	{
		try
		{
			Currency.valueOf(currency.name());
			return true;
		} catch (IllegalArgumentException e)
		{
			return false;
		}
	}
}
