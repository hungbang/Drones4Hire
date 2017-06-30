package com.drones4hire.dronesapp.models.db.payments;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Currency;

public class WithdrawRequest extends AbstractEntity
{
	private static final long serialVersionUID = -7133122639786715914L;

	public enum Status
	{
		NEW, PENDING, APPROVED, CANCELED, FAILED, FUNDED
	}

	private Long userId;
	private Long transactionId;
	private BigDecimal amount;
	private Currency currency;
	private String comment;
	private String adminComment;
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

	public String getAdminComment()
	{
		return adminComment;
	}

	public void setAdminComment(String adminComment)
	{
		this.adminComment = adminComment;
	}
}
