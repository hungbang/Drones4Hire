package com.drones4hire.dronesapp.models.db.projects;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Attachment extends AbstractEntity
{
	private static final long serialVersionUID = -6905100677668763047L;
	
	public enum Type
	{
		PROJECT_ATTACHMENT, PROJECT_RESULT
	}
	
	private String attachmentURL;
	private Long projectId;
	private Type type;

	public String getAttachmentURL()
	{
		return attachmentURL;
	}

	public void setAttachmentURL(String attachmentURL)
	{
		this.attachmentURL = attachmentURL;
	}

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}
}
