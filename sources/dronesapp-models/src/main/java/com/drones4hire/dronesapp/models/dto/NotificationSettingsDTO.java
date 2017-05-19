package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

public class NotificationSettingsDTO extends AbstractDTO
{
	private static final long serialVersionUID = 1914111637907329254L;
	
	@NotNull(message="Plain email required")
	private Boolean plainEmail;
	
	@NotNull(message="Bid placed required")
	private Boolean bidPlaced;
	
	@NotNull(message="Payment received required")
	private Boolean paymentReceived;
	
	@NotNull(message="Project update required")
	private Boolean projectUpdate;
	
	@NotNull(message="Staff required")
	private Boolean staff;
	
	@NotNull(message="Drones news required")
	private Boolean dronesNews;
	
	@NotNull(message="Project award required")
	private Boolean projectAward;
	
	@NotNull(message="Marketing required")
	private Boolean marketing;
	
	@NotNull(message="Deals required")
	private Boolean deals;
	
	@NotNull(message="Monthly news required")
	private Boolean monthlyNews;

	public boolean isPlainEmail()
	{
		return plainEmail;
	}

	public void setPlainEmail(boolean plainEmail)
	{
		this.plainEmail = plainEmail;
	}

	public boolean isBidPlaced()
	{
		return bidPlaced;
	}

	public void setBidPlaced(boolean bidPlaced)
	{
		this.bidPlaced = bidPlaced;
	}

	public boolean isPaymentReceived()
	{
		return paymentReceived;
	}

	public void setPaymentReceived(boolean paymentReceived)
	{
		this.paymentReceived = paymentReceived;
	}

	public boolean isProjectUpdate()
	{
		return projectUpdate;
	}

	public void setProjectUpdate(boolean projectUpdate)
	{
		this.projectUpdate = projectUpdate;
	}

	public boolean isStaff()
	{
		return staff;
	}

	public void setStaff(boolean staff)
	{
		this.staff = staff;
	}

	public boolean isDronesNews()
	{
		return dronesNews;
	}

	public void setDronesNews(boolean dronesNews)
	{
		this.dronesNews = dronesNews;
	}

	public boolean isProjectAward()
	{
		return projectAward;
	}

	public void setProjectAward(boolean projectAward)
	{
		this.projectAward = projectAward;
	}

	public boolean isMarketing()
	{
		return marketing;
	}

	public void setMarketing(boolean marketing)
	{
		this.marketing = marketing;
	}

	public boolean isDeals()
	{
		return deals;
	}

	public void setDeals(boolean deals)
	{
		this.deals = deals;
	}

	public boolean isMonthlyNews()
	{
		return monthlyNews;
	}

	public void setMonthlyNews(boolean monthlyNews)
	{
		this.monthlyNews = monthlyNews;
	}
}