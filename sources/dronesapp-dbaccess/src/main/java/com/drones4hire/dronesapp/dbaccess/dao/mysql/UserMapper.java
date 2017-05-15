package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;

public interface UserMapper
{
	void createUser(User user);
	
	void createUserGroup(User user, Group group);
	
	User getUserById(long id);

	User getUserByUsername(String username);

	List<User> getAllUsers();
	
	void updateUser(User user);

	void deleteUserGroup(long userId);
	
	void deleteUser(long id);
}
