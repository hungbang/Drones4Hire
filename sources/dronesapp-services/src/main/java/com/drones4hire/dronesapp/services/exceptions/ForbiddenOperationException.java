package com.drones4hire.dronesapp.services.exceptions;

public class ForbiddenOperationException extends ServiceException
{
	private static final long serialVersionUID = 8252704605156492146L;

	public ForbiddenOperationException()
	{
		super();
	}

	public ForbiddenOperationException(String message)
	{
		super(message);
	}

	public ForbiddenOperationException(Throwable cause)
	{
		super(cause);
	}

	public ForbiddenOperationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}