package com.drones4hire.admin.dto.forms;

import org.apache.commons.lang3.StringUtils;

public class SigninForm
{
	private String email;

	private String password;
	
	private String passwordResetLink;
	
	private boolean signinFailed;
	
	public SigninForm(String passwordResetLink, boolean signinFailed)
	{
		this.passwordResetLink = passwordResetLink;
		this.signinFailed = signinFailed; 
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = StringUtils.lowerCase(email);
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPasswordResetLink()
	{
		return passwordResetLink;
	}

	public void setPasswordResetLink(String passwordResetLink)
	{
		this.passwordResetLink = passwordResetLink;
	}

	public boolean isSigninFailed()
	{
		return signinFailed;
	}

	public void setSigninFailed(boolean signinFailed)
	{
		this.signinFailed = signinFailed;
	}
}
