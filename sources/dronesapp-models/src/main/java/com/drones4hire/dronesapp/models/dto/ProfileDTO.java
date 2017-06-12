package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;

public class ProfileDTO extends AbstractDTO
{
	private static final long serialVersionUID = -4834910299805231694L;

	private Long userId;
	
	@NotNull(message="Tagline required")
	private String tagline;
	
	@NotNull(message="Bio required")
	private String bio;
	
	@NotNull(message="Web URL required")
	private String webURL;
	
	private String companyLogoURL;
	
	private String coverPhotoURL;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

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
}
