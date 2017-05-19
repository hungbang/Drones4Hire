package com.drones4hire.dronesapp.models.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.commons.Currency;

public class BudgetDTO extends AbstractDTO
{
	private static final long serialVersionUID = -7831240772101353162L;

	@NotNull(message = "Title requried")
	private String title;
	
	@NotNull(message = "Min requried")
	@Min(value = 0, message = "Min budget need to be positive")
	private BigDecimal min;
	
	@NotNull(message = "Max budget requried")
	private BigDecimal max;
	
	@NotNull(message = "Currency requried")
	private Currency currency;
	
	@NotNull(message = "Order requried")
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
}
