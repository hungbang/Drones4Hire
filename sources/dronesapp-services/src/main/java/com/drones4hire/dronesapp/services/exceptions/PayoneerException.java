package com.drones4hire.dronesapp.services.exceptions;

public class PayoneerException extends ServiceException
{
	private static final long serialVersionUID = 201855553642859489L;

	public PayoneerException()
	{
		super();
	}

	public PayoneerException(String message)
	{
		super(message);
	}

	public PayoneerException(Throwable cause)
	{
		super(cause);
	}

	public PayoneerException(String message, Throwable cause)
	{
		super(message, cause);
	}
}