package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Group.Role;

public interface GroupMapper
{
	void createGroup(Group group);

	Group getGroupById(long id);

	Group getGroupByName(String name);
	
	Group getGroupByRole(Role role);

	List<Group> getAllGroups();

	void updateGroup(Group group);

	void deleteGroup(long id);
}
