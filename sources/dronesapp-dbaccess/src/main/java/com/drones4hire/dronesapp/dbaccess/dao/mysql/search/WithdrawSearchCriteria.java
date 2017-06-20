package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest.Status;

public class WithdrawSearchCriteria extends SearchCriteria
{
	private Long userId;
	private BigDecimal amount;
	private Currency currency;
	private Status status;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
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

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}
}