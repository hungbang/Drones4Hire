package com.drones4hire.dronesapp.services.services.notifications;

public enum EmailType
{
	CONFIRMATION("Drones4Hire Account Verification", "EmailConfirmationTemplate.ftl"),
	CHANGE_EMAIL("Drones4Hire Account Verification", "EmailChangeTemplate.ftl"),
	FORGOT_PASSWORD("Drones4Hire Reset Password", "PasswordResetTemplate.ftl"),
	NEW_BID_RECEIVE("You received a new bid", "ReceiveNewBidTemplate.ftl"),
	NEW_BID_PLACE("Your Bid Has Been Placed", "PlaceNewBidTemplate.ftl"),
	REJECT_BID("Your Project Has Been Rejected", "RejectBidTemplate.ftl"),
	ACCEPT_BID("Your Project Has Been Accepted", "AcceptBidTemplate.ftl"),
	AWARD_BID("You're Hired!", "AwardPilotBidTemplate.ftl"),
	UPDATE_BID("Bid Updated Successfully", "UpdateBidTemplate.ftl"),
	RETRACT_BID("Bid Retracted Successfully", "RetractBidTemplate.ftl"),
	QUESTION("User question", "QuestionTemplate.ftl");

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
