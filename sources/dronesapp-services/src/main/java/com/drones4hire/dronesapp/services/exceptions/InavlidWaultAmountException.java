package com.drones4hire.dronesapp.services.exceptions;

public class InavlidWaultAmountException extends ServiceException
{
	private static final long serialVersionUID = 7961201962712759116L;

	public InavlidWaultAmountException()
	{
		super();
	}

	public InavlidWaultAmountException(String message)
	{
		super(message);
	}

	public InavlidWaultAmountException(Throwable cause)
	{
		super(cause);
	}

	public InavlidWaultAmountException(String message, Throwable cause)
	{
		super(message, cause);
	}
}