package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.StateMapper;
import com.drones4hire.dronesapp.models.db.commons.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateService
{

	@Autowired
	private StateMapper stateMapper;

	@Transactional(rollbackFor = Exception.class)
	public State createState(State state)
	{
		stateMapper.createState(state);
		return state;
	}

	@Transactional(readOnly = true)
	public State getStateById(long id)
	{
		return stateMapper.getStateById(id);
	}

	@Transactional(readOnly = true)
	public List<State> getAllStates()
	{
		return stateMapper.getAllStates();
	}

	@Transactional(rollbackFor = Exception.class)
	public State updateState(State state)
	{
		stateMapper.updateState(state);
		return state;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteState(long id)
	{
		stateMapper.deleteState(id);
	}
}
