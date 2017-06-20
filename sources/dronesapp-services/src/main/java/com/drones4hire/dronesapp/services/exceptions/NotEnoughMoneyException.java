package com.drones4hire.dronesapp.services.exceptions;

public class NotEnoughMoneyException extends ServiceException
{

	private static final long serialVersionUID = 7961201962712759116L;

	public NotEnoughMoneyException()
	{
		super();
	}

	public NotEnoughMoneyException(String message)
	{
		super(message);
	}

	public NotEnoughMoneyException(Throwable cause)
	{
		super(cause);
	}

	public NotEnoughMoneyException(String message, Throwable cause)
	{
		super(message, cause);
	}
}