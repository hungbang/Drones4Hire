package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PaidOptionMapper;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaidOptionService
{

	@Autowired
	private PaidOptionMapper paidOptionMapper;

	@Transactional(rollbackFor = Exception.class)
	public PaidOption createPaidOption(PaidOption paidOption)
	{
		paidOptionMapper.createPaidOption(paidOption);
		return paidOption;
	}

	@Transactional(readOnly = true)
	public PaidOption getPaidOptionById(long id)
	{
		return paidOptionMapper.getPaidOptionById(id);
	}

	@Transactional(readOnly = true)
	public List<PaidOption> getAllPaidOptions()
	{
		return paidOptionMapper.getAllPaidOptions();
	}

	@Transactional(rollbackFor = Exception.class)
	public PaidOption updatePaidOption(PaidOption paidOption)
	{
		paidOptionMapper.updatePaidOption(paidOption);
		return paidOption;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePaidOption(long id)
	{
		paidOptionMapper.deletePaidOption(id);
	}
}
