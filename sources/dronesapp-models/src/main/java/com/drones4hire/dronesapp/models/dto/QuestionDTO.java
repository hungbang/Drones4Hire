package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class QuestionDTO extends AbstractDTO
{
	private static final long serialVersionUID = -9070583992085908314L;

	@NotNull(message = "First name required.")
	private String firstName;

	@NotNull(message = "Last name required")
	private String lastName;

	@NotNull(message = "Email address required")
	@Email(message = "Wrong email")
	private String email;

	@NotNull(message = "Phone required")
	private String phone;

	@NotNull(message = "Reason required")
	private String reason;

	@NotNull(message = "Country required")
	private String country;

	@NotNull(message = "Message required")
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