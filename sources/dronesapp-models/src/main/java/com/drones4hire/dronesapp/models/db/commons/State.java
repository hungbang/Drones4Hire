package com.drones4hire.dronesapp.models.db.commons;

import java.util.List;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class State extends AbstractEntity {
	private static final long serialVersionUID = 3967170236154798084L;

	private String name;
	private List<Location> locations;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
