package com.drones4hire.admin.exceptions;

public class ForbiddenOperationException extends Exception
{
	private static final long serialVersionUID = 8271591521737436944L;

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
