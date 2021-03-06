package com.drones4hire.dronesapp.models.db.settings;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class NotificationSettings extends AbstractEntity
{
	private static final long serialVersionUID = 4481040116411703206L;

	private Long userId;
	private boolean plainEmail = true;
	private boolean bidPlaced = true;
	private boolean paymentReceived = false;
	private boolean projectUpdate = false;
	private boolean staff = false;
	private boolean dronesNews = false;
	private boolean projectAward = true;
	private boolean marketing = false;
	private boolean deals = false;
	private boolean monthlyNews = false;

	public NotificationSettings()
	{
	}
	
	public NotificationSettings(Long userId)
	{
		this.userId = userId;
	}
	
	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

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