package com.drones4hire.dronesapp.services.exceptions;

public class InvalidUserStatusException extends ServiceException
{
	private static final long serialVersionUID = 8112912054257072503L;

	public InvalidUserStatusException()
	{
		super();
	}

	public InvalidUserStatusException(String message)
	{
		super(message);
	}

	public InvalidUserStatusException(Throwable cause)
	{
		super(cause);
	}

	public InvalidUserStatusException(String message, Throwable cause)
	{
		super(message, cause);
	}
}