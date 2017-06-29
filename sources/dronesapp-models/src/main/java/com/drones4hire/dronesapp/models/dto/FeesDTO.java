package com.drones4hire.dronesapp.models.dto;

import java.math.BigDecimal;

public class FeesDTO extends AbstractDTO
{
	private static final long serialVersionUID = -7645600251914667876L;

	private BigDecimal serviceFee;
	
	public FeesDTO()
	{
	}

	public FeesDTO(BigDecimal serviceFee)
	{
		this.serviceFee = serviceFee;
	}

	public BigDecimal getServiceFee()
	{
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee)
	{
		this.serviceFee = serviceFee;
	}
}
