package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

public class PaymentTokenDTO extends AbstractDTO
{
	private static final long serialVersionUID = 5729249067868979799L;
	
	@NotNull(message = "Payment method required")
	private String paymentMethod;

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}
}