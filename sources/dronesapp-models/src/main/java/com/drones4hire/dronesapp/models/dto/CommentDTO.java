package com.drones4hire.dronesapp.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentDTO extends AbstractDTO
{

	private static final long serialVersionUID = 5121831616695982618L;

	@NotNull(message = "Project id required")
	private Long projectId;

	@NotNull(message = "Comment required")
	@Size(min = 1, message = "Wrong comment size")
	private String comment;

	public Long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
