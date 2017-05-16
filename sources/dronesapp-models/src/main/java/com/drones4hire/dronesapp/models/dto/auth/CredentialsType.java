package com.drones4hire.dronesapp.models.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class CredentialsType implements Serializable
{
	private static final long serialVersionUID = 1567014101763491651L;
	
	@NotNull
	private String email;
	
	@NotNull
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
