package com.drones4hire.dronesapp.models.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class CredentialsDTO implements Serializable
{
	private static final long serialVersionUID = 1567014101763491651L;
	
	@NotNull(message = "Email address shouldn't be null")
	@Size(max = 10, min = 5, message = "Wrong email size")
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
