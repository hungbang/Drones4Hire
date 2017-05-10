package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.User;

public interface UserMapper
{
	User getUserById(long id);

	User getUserByUserName(String username);
}
