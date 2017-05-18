package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.BudgetMapper;
import com.drones4hire.dronesapp.models.db.commons.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BudgetService
{

	@Autowired
	private BudgetMapper budgetMapper;

	@Transactional(rollbackFor = Exception.class)
	public Budget createBudget(Budget budget)
	{
		budgetMapper.createBudget(budget);
		return budget;
	}

	@Transactional(readOnly = true)
	public Budget getBudgetById(long id)
	{
		return budgetMapper.getBudgetById(id);
	}

	@Transactional(readOnly = true)
	public List<Budget> getAllBudgets()
	{
		return budgetMapper.getAllBudgets();
	}

	@Transactional(rollbackFor = Exception.class)
	public Budget updateBudget(Budget budget)
	{
		budgetMapper.updateBudget(budget);
		return budget;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteBudget(long id)
	{
		budgetMapper.deleteBudget(id);
	}
}
