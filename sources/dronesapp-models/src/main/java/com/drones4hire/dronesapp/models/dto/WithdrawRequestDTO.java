package com.drones4hire.dronesapp.models.dto;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest.Status;

public class WithdrawRequestDTO extends AbstractDTO
{
	private static final long serialVersionUID = -7133122639786715914L;

	private Long userId;
	private Long transactionId;
	private BigDecimal amount;
	private Currency currency;
	private String comment;
	private Status status;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(Long transactionId)
	{
		this.transactionId = transactionId;
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

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
}
