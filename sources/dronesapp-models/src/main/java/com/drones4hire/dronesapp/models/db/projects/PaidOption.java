package com.drones4hire.dronesapp.models.db.projects;

import java.math.BigDecimal;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Currency;

public class PaidOption extends AbstractEntity implements Comparable<PaidOption>
{
	private static final long serialVersionUID = 5512623503679003897L;

	private String title;
	private String description;
	private BigDecimal price;
	private Currency currency;
	private Integer rating;

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

	public Integer getRating()
	{
		return rating;
	}

	public void setRating(Integer rating)
	{
		this.rating = rating;
	}

	@Override
	public boolean equals(Object o)
	{
		boolean equals = false;
		if(o != null && o instanceof PaidOption && this.getId() != null)
		{
			equals = this.getId().equals(((PaidOption) o).getId());
		}
		return equals;
	}
	
	@Override
	public int hashCode()
	{
		return this.getId().intValue();
	}
	

	@Override
	public int compareTo(PaidOption o)
	{
		return this.getId() > o.getId() ? 1 : -1;
	}
}
