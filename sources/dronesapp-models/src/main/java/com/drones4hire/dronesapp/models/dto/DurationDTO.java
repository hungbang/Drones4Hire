package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DurationDTO extends AbstractDTO
{
	private static final long serialVersionUID = -6156152405878066391L;

	@NotNull(message = "Title required")
	private String title;
	
	@NotNull(message = "Min required")
	@Min(value = 0, message = "Min duration need to be positive")
	private Integer min;
	
	@NotNull(message = "Max duration required")
	private Integer max;
	
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
