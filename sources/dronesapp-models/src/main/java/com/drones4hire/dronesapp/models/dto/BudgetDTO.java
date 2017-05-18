package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BudgetDTO extends AbstractDTO
{

	private static final long serialVersionUID = -7831240772101353162L;

	@NotNull(message = "Title shouldn't be null")
	private String title;
	@NotNull(message = "Min shouldn't be null")
	@Min(0)
	private BigDecimal min;
	@NotNull(message = "Max shouldn't be null")
	private BigDecimal max;
	@NotNull(message = "Currency shouldn't be null")
	private Currency currency;
	@NotNull(message = "Order shouldn't be null")
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
