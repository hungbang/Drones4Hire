package com.drones4hire.dronesapp.services.exceptions;

public class PaymentException extends ServiceException
{
	private static final long serialVersionUID = 4366647980143035188L;

	public PaymentException()
	{
		super();
	}

	public PaymentException(String message)
	{
		super(message);
	}

	public PaymentException(Throwable cause)
	{
		super(cause);
	}

	public PaymentException(String message, Throwable cause)
	{
		super(message, cause);
	}
}