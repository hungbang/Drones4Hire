package com.drones4hire.dronesapp.services.services.notifications;

public enum EmailType
{
	EMAIL_CONFIRMATION("Drones4Hire Account Verification", "EmailConfirmationTemplate.ftl");

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
