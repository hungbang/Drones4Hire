package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Profile extends AbstractEntity
{
	private static final long serialVersionUID = -5625523474921513382L;

	private Long userId;
	private String tagline;
	private String bio;
	private String webURL;
	private String companyLogoURL;
	private String coverPhotoURL;

	public String getTagline()
	{
		return tagline;
	}

	public void setTagline(String tagline)
	{
		this.tagline = tagline;
	}

	public String getBio()
	{
		return bio;
	}

	public void setBio(String bio)
	{
		this.bio = bio;
	}

	public String getWebURL()
	{
		return webURL;
	}

	public void setWebURL(String webURL)
	{
		this.webURL = webURL;
	}

	public String getCompanyLogoURL()
	{
		return companyLogoURL;
	}

	public void setCompanyLogoURL(String companyLogoURL)
	{
		this.companyLogoURL = companyLogoURL;
	}

	public String getCoverPhotoURL()
	{
		return coverPhotoURL;
	}

	public void setCoverPhotoURL(String coverPhotoURL)
	{
		this.coverPhotoURL = coverPhotoURL;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
}
