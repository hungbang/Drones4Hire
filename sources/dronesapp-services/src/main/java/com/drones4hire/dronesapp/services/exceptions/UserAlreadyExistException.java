package com.drones4hire.dronesapp.services.exceptions;

public class UserAlreadyExistException extends ServiceException
{
	private static final long serialVersionUID = 8956608165482445219L;

	public UserAlreadyExistException()
	{
		super();
	}

	public UserAlreadyExistException(String message)
	{
		super(message);
	}

	public UserAlreadyExistException(Throwable cause)
	{
		super(cause);
	}

	public UserAlreadyExistException(String message, Throwable cause)
	{
		super(message, cause);
	}
}