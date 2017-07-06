package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ServiceFeeMapper;
import com.drones4hire.dronesapp.models.db.payments.ServiceFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceFeeService
{

	@Autowired
	private ServiceFeeMapper serviceFeeMapper;

	@Transactional(rollbackFor = Exception.class)
	public ServiceFee createServiceFee(ServiceFee serviceFee)
	{
		serviceFeeMapper.createServiceFee(serviceFee);
		return serviceFee;
	}

	@Transactional(readOnly = true)
	public ServiceFee getServiceFeeById(long id)
	{
		return serviceFeeMapper.getServiceFeeById(id);
	}

	@Transactional(readOnly = true)
	public List<ServiceFee> getAllServiceFees()
	{
		return serviceFeeMapper.getAllServiceFees();
	}

	@Transactional(rollbackFor = Exception.class)
	public ServiceFee updateServiceFee(ServiceFee serviceFee)
	{
		serviceFeeMapper.updateServiceFee(serviceFee);
		return serviceFee;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteServiceFee(long id)
	{
		serviceFeeMapper.deleteServiceFee(id);
	}
}
