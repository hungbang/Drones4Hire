package com.drones4hire.dronesapp.models.db.projects;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Attachments extends AbstractEntity
{
	private static final long serialVersionUID = -6905100677668763047L;
	
	public enum TYPE
	{
		PROJECT_ATTACH, RESULT_ATTACH
	}
	
	private String attachmentURL;
	private Long projectId;

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

}
