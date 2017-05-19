package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BudgetDTO extends AbstractDTO
{
	private static final long serialVersionUID = -7831240772101353162L;

	@NotNull(message = "Title required")
	private String title;
	
	@NotNull(message = "Min required")
	@Min(value = 0, message = "Min price need to be positive")
	private BigDecimal min;
	
	@NotNull(message = "Max required")
	private BigDecimal max;
	
	@NotNull(message = "Currency required")
	private Currency currency;
	
	@NotNull(message = "Order required")
	private Integer order;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public BigDecimal getMin()
	{
		return min;
	}

	public void setMin(BigDecimal min)
	{
		this.min = min;
	}

	public BigDecimal getMax()
	{
		return max;
	}

	public void setMax(BigDecimal max)
	{
		this.max = max;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public Integer getOrder()
	{
		return order;
	}

	public void setOrder(Integer order)
	{
		this.order = order;
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
