package com.drones4hire.dronesapp.models.dto;

public class PaymentClientDTO extends AbstractDTO
{
	private static final long serialVersionUID = 2065255608682698950L;
	
	private String clientId;
	
	public PaymentClientDTO()
	{
	}
	
	public PaymentClientDTO(String clientId)
	{
		this.clientId = clientId;
	}

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
}