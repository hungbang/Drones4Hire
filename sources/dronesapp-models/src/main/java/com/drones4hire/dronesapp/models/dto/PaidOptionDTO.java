package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PaidOptionDTO extends AbstractDTO
{
	private static final long serialVersionUID = 229985081766669727L;

	@NotNull(message = "Title required")
	private String title;
	
	@NotNull(message = "Description required")
	private String description;
	
	@NotNull(message = "Price required")
	@Min(value = 0, message = "Price should be positive")
	private BigDecimal price;
	
	@NotNull(message = "Currency required")
	private Currency currency;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
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
