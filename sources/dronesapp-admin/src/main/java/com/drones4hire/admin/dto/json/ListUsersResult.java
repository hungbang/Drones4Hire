package com.drones4hire.admin.dto.json;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.User;

public class ListUsersResult extends AbstractListResult
{
	private List<User> users;

	public ListUsersResult(List<User> users)
	{
		super(users != null ? users.size() : 0);
		this.users = users;
	}

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}
}
