package com.drones4hire.dronesapp.models.db.settings;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class NotificationSettings extends AbstractEntity {
	private static final long serialVersionUID = 4481040116411703206L;

	private Long userId;
	private Boolean plainEmail;
	private Boolean bidPlaced;
	private Boolean paymentReceived;
	private Boolean projectUpdate;
	private Boolean staff;
	private Boolean dronesNews;
	private Boolean projectAward;
	private Boolean marketing;
	private Boolean deals;
	private Boolean monthlyNews;

	public Boolean isPlainEmail() {
		return plainEmail;
	}

	public void setPlainEmail(Boolean plainEmail) {
		this.plainEmail = plainEmail;
	}

	public Boolean isBidPlaced() {
		return bidPlaced;
	}

	public void setBidPlaced(Boolean bidPlaced) {
		this.bidPlaced = bidPlaced;
	}

	public Boolean isPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(Boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}

	public Boolean isProjectUpdate() {
		return projectUpdate;
	}

	public void setProjectUpdate(Boolean projectUpdate) {
		this.projectUpdate = projectUpdate;
	}

	public Boolean isStaff() {
		return staff;
	}

	public void setStaff(Boolean staff) {
		this.staff = staff;
	}

	public Boolean isDronesNews() {
		return dronesNews;
	}

	public void setDronesNews(Boolean dronesNews) {
		this.dronesNews = dronesNews;
	}

	public Boolean isProjectAward() {
		return projectAward;
	}

	public void setProjectAward(Boolean projectAward) {
		this.projectAward = projectAward;
	}

	public Boolean isMarketing() {
		return marketing;
	}

	public void setMarketing(Boolean marketing) {
		this.marketing = marketing;
	}

	public Boolean isDeals() {
		return deals;
	}

	public void setDeals(Boolean deals) {
		this.deals = deals;
	}

	public Boolean isMonthlyNews() {
		return monthlyNews;
	}

	public void setMonthlyNews(Boolean monthlyNews) {
		this.monthlyNews = monthlyNews;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
