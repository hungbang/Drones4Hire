package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class PilotLicense extends AbstractEntity {
	private static final long serialVersionUID = -3845232480700290476L;

	private Long pilotId;
	private String licenseUrl;
	private String insuranceUrl;
	private Boolean verified;

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getInsuranceUrl() {
		return insuranceUrl;
	}

	public void setInsuranceUrl(String insuranceUrl) {
		this.insuranceUrl = insuranceUrl;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Long getPilotId() {
		return pilotId;
	}

	public void setPilotId(Long pilotId) {
		this.pilotId = pilotId;
	}
}
