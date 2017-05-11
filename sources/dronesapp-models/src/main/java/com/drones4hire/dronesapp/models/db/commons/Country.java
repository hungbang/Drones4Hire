package com.drones4hire.dronesapp.models.db.commons;

import java.util.List;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Country extends AbstractEntity {
	private static final long serialVersionUID = -2091202396207453304L;

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
