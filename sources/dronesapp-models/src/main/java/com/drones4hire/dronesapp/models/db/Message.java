package com.drones4hire.dronesapp.models.db;

public class Message extends AbstractEntity
{
	private static final long serialVersionUID = 6246098892813154627L;
	
	public enum Type
	{
		EMAIL, INTERNAL
	}
	
	private String message;
	private Long fromUserId;
	private Long toUserId;
	private Type type;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Long getFromUserId()
	{
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId)
	{
		this.fromUserId = fromUserId;
	}

	public Long getToUserId()
	{
		return toUserId;
	}

	public void setToUserId(Long toUserId)
	{
		this.toUserId = toUserId;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}
}