package com.drones4hire.dronesapp.models.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CredentialsDTO implements Serializable
{
	private static final long serialVersionUID = 1567014101763491651L;
	
	@NotNull(message = "notNull.erorr.message")
	@Size(max = 10, min = 5, message = "size.error.message")
	private String email;
	
	@NotNull(message = "notNull.erorr.message")
	@Size(max = 30, min = 3, message = "size.error.message")
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
