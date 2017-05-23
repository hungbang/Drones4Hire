package com.drones4hire.dronesapp.services.services.notifications;

public enum EmailType
{
	CONFIRMATION("Drones4Hire Account Verification", "EmailConfirmationTemplate.ftl"),
	CHANGE_EMAIL("Drones4Hire Account Verification", "EmailChangeTemplate.ftl"),
	FORGOT_PASSWORD("Drones4Hire Reset Password", "PasswordResetTemplate.ftl");

	private String subject;
	private String templatePath;

	private EmailType(String subject, String templatePath)
	{
		this.subject = subject;
		this.templatePath = templatePath;
	}

	public String subject()
	{
		return this.subject;
	}

	public String templatePath()
	{
		return this.templatePath;
	}
}
