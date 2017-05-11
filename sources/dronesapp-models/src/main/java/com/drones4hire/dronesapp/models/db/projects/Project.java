package com.drones4hire.dronesapp.models.db.projects;

import java.util.Date;
import java.util.List;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.services.Service;
import com.drones4hire.dronesapp.models.db.users.Pilot;
import com.drones4hire.dronesapp.models.db.users.User;

public class Project extends AbstractEntity {
	private static final long serialVersionUID = 7209775795910871836L;

	public enum Status {
		NEW, COMPLETE
	}

	private Service service;
	private Duration duration;
	private Location location;
	private String title;
	private String summary;
	private User client;
	private Pilot pilot;
	private Budget budget;
	private String imageUrl;
	private Boolean postProdactionRequired;
	private Date startDate;
	private Status status;
	private List<Transaction> transactions;
	private List<PaidOption> paidOptions;
	private List<Comment> comments;
	private List<Bid> bids;
	private List<Feedback> feedbacks;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getPostProdactionRequired() {
		return postProdactionRequired;
	}

	public void setPostProdactionRequired(Boolean postProdactionRequired) {
		this.postProdactionRequired = postProdactionRequired;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<PaidOption> getPaidOptions() {
		return paidOptions;
	}

	public void setPaidOptions(List<PaidOption> paidOptions) {
		this.paidOptions = paidOptions;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
}
