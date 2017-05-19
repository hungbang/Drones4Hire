package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.commons.Currency;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PaidOptionDTO extends AbstractDTO
{

	private static final long serialVersionUID = 229985081766669727L;

	@NotNull(message = "Title shouldn't be null")
	private String title;
	@NotNull(message = "Description shouldn't be null")
	private String description;
	@NotNull(message = "Price shouldn't be null")
	@Min(value = 0, message = "Price shouldn't be less than '0'")
	private BigDecimal price;
	@NotNull(message = "Currency shouldn't be null")
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
