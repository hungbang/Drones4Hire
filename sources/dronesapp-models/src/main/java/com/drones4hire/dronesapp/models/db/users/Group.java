package com.drones4hire.dronesapp.models.db.users;

import com.drones4hire.dronesapp.models.db.AbstractEntity;

public class Group extends AbstractEntity
{
	private static final long serialVersionUID = -1122566583572312653L;

	private String name;
	private Role role;

	public Group()
	{
	}

	public Group(Role role)
	{
		this.role = role;
	}

	public enum Role
	{
		ROLE_CLIENT, ROLE_PILOT, ROLE_ADMIN
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}
}
