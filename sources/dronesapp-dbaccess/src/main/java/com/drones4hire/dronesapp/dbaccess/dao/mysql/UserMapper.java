package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;

public interface UserMapper
{
	void createUser(User user);
	
	void createUserGroup(@Param("user") User user, @Param("group") Group group);
	
	User getUserById(Long id);

	User getUserByUsername(String username);
	
	User getUserByEmail(String email);

	List<User> searchUsers(UserSearchCriteria sc);
	
	List<User> getAllUsers();
	
	void updateUser(User user);

	void deleteUserGroup(@Param("userId") Long userId, @Param("groupId") Long groupId);
	
	void deleteUser(Long id);
}
