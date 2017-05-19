package com.drones4hire.dronesapp.models.dto.error;

import com.drones4hire.dronesapp.models.dto.AbstractDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DurationDTO extends AbstractDTO
{

	@NotNull(message = "Title shouldn't be null")
	private String title;
	@NotNull(message = "Min shouldn't be null")
	@Min(value = 0, message = "Min shouldn't be less than '0'")
	private Integer min;
	@NotNull(message = "Max shouldn't be null")
	private Integer max;
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

	public Integer getMin()
	{
		return min;
	}

	public void setMin(Integer min)
	{
		this.min = min;
	}

	public Integer getMax()
	{
		return max;
	}

	public void setMax(Integer max)
	{
		this.max = max;
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
