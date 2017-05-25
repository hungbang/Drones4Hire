package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.drones4hire.dronesapp.models.db.commons.Currency;

import static com.drones4hire.dronesapp.models.db.payments.Transaction.Type;
import static com.drones4hire.dronesapp.models.db.payments.Transaction.Status;

import java.math.BigDecimal;

public class TransactionSearchCriteria extends SearchCriteria
{
	
	private Long walletId;
	private BigDecimal amount;
	private Currency currency;
	private Type type;
	private String purpose;
	private Long projectId;
	private Status status;

	public Long getWalletId()
	{
		return walletId;
	}

	public void setWalletId(Long walletId)
	{
		this.walletId = walletId;
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

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
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
