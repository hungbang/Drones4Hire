package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.drones4hire.dronesapp.models.db.projects.Attachment.Type;

public class AttachmentDTO extends AbstractDTO
{
	private static final long serialVersionUID = -6905100677668763047L;
	
	private String title;
	@NotNull(message = "Attachment URL required")
	private String attachmentURL;
	@NotNull(message = "Project Id required")
	private Long projectId;
	@NotNull(message = "Type required")
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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@AssertTrue(message = "Title is required")
	public boolean isTitleValid()
	{
		if(type.equals(Type.PROJECT_RESULT))
			return title != null;
		else 
			return true;
	}
}
