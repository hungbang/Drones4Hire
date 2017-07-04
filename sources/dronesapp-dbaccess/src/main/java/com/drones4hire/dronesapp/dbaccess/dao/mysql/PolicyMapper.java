package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.content.Policy;

import java.util.List;

public interface PolicyMapper
{

	void createPolicy(Policy Policy);

	Policy getPolicyById(long id);

	List<Policy> getAllPolicy();

	void updatePolicy(Policy Policy);

	void deletePolicy(long id);
}
