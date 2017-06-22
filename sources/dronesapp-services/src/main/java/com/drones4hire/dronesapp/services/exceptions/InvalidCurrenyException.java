package com.drones4hire.dronesapp.services.exceptions;

public class InvalidCurrenyException extends ServiceException
{
	private static final long serialVersionUID = 7961201962712759116L;

	public InvalidCurrenyException()
	{
		super();
	}

	public InvalidCurrenyException(String message)
	{
		super(message);
	}

	public InvalidCurrenyException(Throwable cause)
	{
		super(cause);
	}

	public InvalidCurrenyException(String message, Throwable cause)
	{
		super(message, cause);
	}
}