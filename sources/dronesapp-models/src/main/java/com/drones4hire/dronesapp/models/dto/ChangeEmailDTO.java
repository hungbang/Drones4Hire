package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class ChangeEmailDTO extends AbstractDTO
{
	private static final long serialVersionUID = -2459769026424095587L;

	@NotNull(message = "Email address shouldn't be null")
	@Email(message="Wrong email address")
	private String email;
	
	@NotNull(message = "Password shouldn't be null")
	@Size(max = 30, min = 6, message = "Wrong password size")
	private String password;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
