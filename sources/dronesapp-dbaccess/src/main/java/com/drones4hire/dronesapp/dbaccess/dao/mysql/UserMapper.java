package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.User;

public interface UserMapper
{
	User getUserById(long id);

	User getUserByUserName(String username);
	
	void createUser(User user);

	List<User> getAllUsers();
	
	void updateUser(User user);

	void deleteUser(long id);
}
