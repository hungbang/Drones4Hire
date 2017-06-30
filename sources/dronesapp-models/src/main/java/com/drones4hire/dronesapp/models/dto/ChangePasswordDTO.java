package com.drones4hire.dronesapp.models.dto;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordDTO implements Serializable
{
	private static final long serialVersionUID = 2478362544957348484L;

	@NotNull(message = "Password shouldn't be null")
	private String currentPassword;

	@NotNull(message = "New password shouldn't be null")
	@Size(min = 6, message = "Wrong password size")
	private String newPassword;

	@NotNull(message = "Confirm password shouldn't be null")
	@Size(min = 6, message = "Wrong confirm password size")
	private String confirmPassword;

	public String getCurrentPassword()
	{
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword)
	{
		this.currentPassword = currentPassword;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
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
		return newPassword != null && newPassword.equals(confirmPassword);
	}
}
