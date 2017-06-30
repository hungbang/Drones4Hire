package com.drones4hire.dronesapp.models.dto;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordDTO implements Serializable
{
	private static final long serialVersionUID = 2478362544957348484L;
	
	@NotNull(message = "Password shouldn't be null")
	@Size(min = 6, message = "Wrong password size")
	private String password;
	
	@NotNull(message = "Confirm password shouldn't be null")
	@Size(min = 6, message = "Wrong confirm password size")
	private String confirmPassword;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}
	
	@AssertTrue(message = "Password confirmation not matching")
	public boolean isConfirmationValid()
	{
		return password != null && password.equals(confirmPassword);
	}
}
