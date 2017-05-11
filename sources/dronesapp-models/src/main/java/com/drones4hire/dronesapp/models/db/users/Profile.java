package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Profile extends AbstractEntity {
	private static final long serialVersionUID = -5625523474921513382L;

	private Long userId;
	private String tagline;
	private String bio;
	private String webUrl;
	private String companyLogoUrl;
	private String coverPhotoUrl;

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}

	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}

	public String getCoverPhotoUrl() {
		return coverPhotoUrl;
	}

	public void setCoverPhotoUrl(String coverPhotoUrl) {
		this.coverPhotoUrl = coverPhotoUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
