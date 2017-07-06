package com.drones4hire.dronesapp.models.db.payments;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Currency;

import java.math.BigDecimal;

public class ServiceFee extends AbstractEntity
{

	private static final long serialVersionUID = 8715816515648547969L;

	public enum Type
	{
		BRAINTREE, PAYONEER, DRONES
	}

	private Type type;
	private BigDecimal percentage;
	private BigDecimal fixed;
	private Currency currency;

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public BigDecimal getPercentage()
	{
		return percentage;
	}

	public void setPercentage(BigDecimal percentage)
	{
		this.percentage = percentage;
	}

	public BigDecimal getFixed()
	{
		return fixed;
	}

	public void setFixed(BigDecimal fixed)
	{
		this.fixed = fixed;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}
}
