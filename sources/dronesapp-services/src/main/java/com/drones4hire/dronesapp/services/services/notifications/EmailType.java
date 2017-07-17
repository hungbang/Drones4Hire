package com.drones4hire.dronesapp.services.services.notifications;

public enum EmailType
{
	CONFIRMATION("Drones4Hire Account Verification", "EmailConfirmationTemplate.ftl"),
	PILOT_APPROVED("License approved and access granted", "PilotApprovedEmail.ftl"),
	FORGOT_PASSWORD("Drones4Hire Reset Password", "PasswordResetTemplate.ftl"),
	NEW_BID_RECEIVE("You received a new bid", "ReceiveNewBidTemplate.ftl"),
	NEW_COMMENT_RECEIVE("You received a new comment", "ReceiveNewCommentTemplate.ftl"),
	CLIENT_EMAIL_CONFIRMED("Welcome to Drones4Hire", "ClientEmailConfirmedTemplate.ftl"),
	UP_EMAIL_CONFIRMATION("Verify your account", "UpEmailConfirmationTemplate.ftl"),
	AWARD_BID("You're Hired!", "AwardPilotBidTemplate.ftl"),
	ACCEPT_BID("Pilot submits a request for your bid", "AcceptBidTemplate.ftl"),
	RELEASE_PAYMENT("Payment released", "ReleasePaymentTemplate.ftl"),
	UPLOAD_PROJECT_RESULT("New files uploaded", "UploadProjectResultTemplate.ftl"),
	SUPPORT_MESSAGE("Support message", "SupportMessageTemplate.ftl"),
	QUESTION("User question", "QuestionTemplate.ftl"),
	REJECT_BID("Your Project Has Been Rejected", "RejectBidTemplate.ftl"),
	SUBMIT_PAYMENT("Payment released", "SubmitPaymentTemplate.ftl"),
	PROJECT_POSTED("New project posted", "ProjectPostedTemplate.ftl"),
	USER_RESTORE("Try a new Drones4Hire", "RestoreUserTemplate.ftl"),
	JOB_EXPIRATION("Your job was expired", "ExpiredJobTemplate.ftl");

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
