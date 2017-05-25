package com.drones4hire.dronesapp.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class PilotServicesDTO implements Serializable
{
	private static final long serialVersionUID = -7269335250108270005L;

	@NotNull(message = "Service IDs required")
	private List<Long> serviceIds = new ArrayList<>();

	public List<Long> getServiceIds()
	{
		return serviceIds;
	}

	public void setServiceIds(List<Long> serviceIds)
	{
		this.serviceIds = serviceIds;
	}
}
