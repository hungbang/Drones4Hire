package com.drones4hire.dronesapp.models.db.payments;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Currency;

public class Wallet extends AbstractEntity
{
	private static final long serialVersionUID = 5407593528058423401L;

	private Long userId;
	private BigDecimal balance;
	private Currency currency;
	private String paymentToken;
	private String withdrawToken;
	private Boolean withdrawEnabled;

	public Wallet()
	{
	}
	
	public Wallet(Long userId)
	{
		this.userId = userId;
	}
	
	public BigDecimal getBalance()
	{
		return balance;
	}

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public String getPaymentToken()
	{
		return paymentToken;
	}

	public void setPaymentToken(String paymentToken)
	{
		this.paymentToken = paymentToken;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getWithdrawToken()
	{
		return withdrawToken;
	}

	public void setWithdrawToken(String withdrawToken)
	{
		this.withdrawToken = withdrawToken;
	}
	
	public void chageBalance(BigDecimal byAmount)
	{
		this.balance.add(byAmount);
	}
	
	public Boolean isWithdrawEnabled()
	{
		return withdrawEnabled;
	}

	public void setWithdrawEnabled(Boolean withdrawEnabled)
	{
		this.withdrawEnabled = withdrawEnabled;
	}
}
