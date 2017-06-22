package com.drones4hire.dronesapp.models.dto;

import com.drones4hire.dronesapp.models.db.users.PilotEquipment.Type;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

public class PilotEquipmentDTO extends AbstractDTO
{
	private static final long serialVersionUID = 4496012219073056226L;

	private Long userId;

	@NotNull(message = "Name required")
	private String name;

	@NotNull(message = "Type required")
	private Type type;

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	@AssertTrue(message = "Type ENUM confirmation not matching")
	public boolean isConfirmationValid()
	{
		try
		{
			Type.valueOf(type.name());
			return true;
		} catch (IllegalArgumentException e)
		{
			return false;
		}
	}
}
