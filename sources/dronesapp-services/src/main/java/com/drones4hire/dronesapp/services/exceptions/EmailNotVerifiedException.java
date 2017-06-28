package com.drones4hire.dronesapp.services.exceptions;

public class EmailNotVerifiedException extends ServiceException
{
	private static final long serialVersionUID = -31940248415859817L;

	public EmailNotVerifiedException()
	{
		super();
	}

	public EmailNotVerifiedException(String message)
	{
		super(message);
	}

	public EmailNotVerifiedException(Throwable cause)
	{
		super(cause);
	}

	public EmailNotVerifiedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}