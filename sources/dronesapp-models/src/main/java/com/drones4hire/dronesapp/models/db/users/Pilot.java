package com.drones4hire.dronesapp.models.db.users;

import java.util.List;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.models.db.services.Service;

public class Pilot extends User {
	private static final long serialVersionUID = -6642463506755504559L;

	private PilotLicense license;
	private Profile profile;
	private List<PilotLocation> locations;
	private List<PortfolioItem> portfolioItems;
	private List<Service> services;

	public PilotLicense getLicense() {
		return license;
	}

	public void setLicense(PilotLicense license) {
		this.license = license;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<PilotLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<PilotLocation> locations) {
		this.locations = locations;
	}

	public List<PortfolioItem> getPortfolioItems() {
		return portfolioItems;
	}

	public void setPortfolioItems(List<PortfolioItem> portfolioItems) {
		this.portfolioItems = portfolioItems;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
}
