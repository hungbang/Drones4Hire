package com.drones4hire.dronesapp.models.aws;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadObject 
{
	public enum Type
	{
		USER_PHOTO, LICENSE, INSURANCE, COMPANY_LOGO, COVER_PHOTO, PROJECT_ATTACHMENTS, PORTFOLIO, PROJECT_RESULTS
	}

	private Long userId;
	private MultipartFile file;
	private Type type;

	public FileUploadObject(Long userId, MultipartFile file, Type type) {
		this.userId = userId;
		this.file = file;
		this.type = type;
	}
	
	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
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