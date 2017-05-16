package com.drones4hire.dronesapp.services.exceptions;

public class UserNotConfirmedException extends ServiceException
{
	private static final long serialVersionUID = -2042438250109890106L;

	public UserNotConfirmedException()
	{
		super();
	}

	public UserNotConfirmedException(String message)
	{
		super(message);
	}

	public UserNotConfirmedException(Throwable cause)
	{
		super(cause);
	}

	public UserNotConfirmedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}