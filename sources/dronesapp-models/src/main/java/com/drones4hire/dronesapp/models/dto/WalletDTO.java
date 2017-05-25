package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WalletDTO extends AbstractDTO
{

	private static final long serialVersionUID = -8000845218223932945L;

	@NotNull(message = "Balance required")
	@Min(value = 0, message = "Balance should be positive")
	private BigDecimal balance;
	@NotNull(message = "Currency required")
	private Currency currency;
	@JsonIgnore
	private String paymentToken;

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
