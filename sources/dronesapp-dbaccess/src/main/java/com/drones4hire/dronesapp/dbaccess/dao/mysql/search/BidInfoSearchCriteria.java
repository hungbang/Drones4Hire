package com.drones4hire.dronesapp.dbaccess.dao.mysql.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BidInfoSearchCriteria extends SearchCriteria
{
	@JsonIgnore
	private Long clientId;

	public Long getClientId()
	{
		return clientId;
	}

	public void setClientId(Long clientId)
	{
		this.clientId = clientId;
	}
}
