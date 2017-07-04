package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PolicyMapper;
import com.drones4hire.dronesapp.models.db.content.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolicyService
{

	@Autowired
	private PolicyMapper policyMapper;

	@Transactional(rollbackFor = Exception.class)
	public Policy createPolicy(Policy policy)
	{
		policyMapper.createPolicy(policy);
		return policy;
	}

	@Transactional(readOnly = true)
	public Policy getPolicyById(long id)
	{
		return policyMapper.getPolicyById(id);
	}

	@Transactional(readOnly = true)
	public List<Policy> getAllPolicy()
	{
		return policyMapper.getAllPolicy();
	}

	@Transactional(rollbackFor = Exception.class)
	public Policy updatePolicy(Policy policy)
	{
		policyMapper.updatePolicy(policy);
		return policy;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePolicy(long id)
	{
		policyMapper.deletePolicy(id);
	}
}
