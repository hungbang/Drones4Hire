package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.ServiceFee;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ServiceFeeDTO extends AbstractDTO
{

	private static final long serialVersionUID = 7750548461097092590L;

	@NotNull(message = "Type required")
	private ServiceFee.Type type;

	private BigDecimal percentage;
	private BigDecimal fixed;

	@NotNull(message = "Currency required")
	private Currency currency;

	public ServiceFee.Type getType()
	{
		return type;
	}

	public void setType(ServiceFee.Type type)
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

	@AssertTrue(message = "Request should be have fee as percentage and/or a fixed fee")
	public boolean isConfirmationValid()
	{
		return (this.percentage == null && this.fixed != null) || (this.percentage != null && fixed == null)
				|| (this.percentage != null && this.fixed != null);
	}
}
