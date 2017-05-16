package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.GroupMapper;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Group.Role;

@Service
public class GroupService
{

	@Autowired
	private GroupMapper groupMapper;

	@Transactional(rollbackFor = Exception.class)
	public Group createGroup(Group group)
	{
		groupMapper.createGroup(group);
		return group;
	}

	@Transactional(readOnly = true)
	public Group getGroupById(long id)
	{
		return groupMapper.getGroupById(id);
	}

	@Transactional(readOnly = true)
	public Group getGroupByName(String name)
	{
		return groupMapper.getGroupByName(name);
	}
	
	@Transactional(readOnly = true)
	public Group getGroupByRole(Role role)
	{
		return groupMapper.getGroupByRole(role);
	}

	@Transactional(readOnly = true)
	public List<Group> getAllGroups()
	{
		return groupMapper.getAllGroups();
	}

	@Transactional(rollbackFor = Exception.class)
	public Group updateGroup(Group group)
	{
		groupMapper.updateGroup(group);
		return group;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteGroup(long id)
	{
		groupMapper.deleteGroup(id);
	}
}
