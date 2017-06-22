package com.drones4hire.dronesapp.models.db;

public class Question extends AbstractEntity
{
	private static final long serialVersionUID = 6462095233888371874L;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String reason;

	private String country;

	private String message;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}