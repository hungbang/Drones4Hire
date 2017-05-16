package com.drones4hire.dronesapp.services.exceptions;

public class InvalidUserCredentialsException extends ServiceException
{
	private static final long serialVersionUID = -7856354840147317283L;

	public InvalidUserCredentialsException()
	{
		super();
	}

	public InvalidUserCredentialsException(String message)
	{
		super(message);
	}

	public InvalidUserCredentialsException(Throwable cause)
	{
		super(cause);
	}

	public InvalidUserCredentialsException(String message, Throwable cause)
	{
		super(message, cause);
	}
}