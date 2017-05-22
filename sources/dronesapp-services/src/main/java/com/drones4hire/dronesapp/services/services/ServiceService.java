package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ServiceMapper;
import com.drones4hire.dronesapp.models.db.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService
{

	@Autowired
	private ServiceMapper serviceMapper;

	@Transactional(rollbackFor = Exception.class)
	public Service createService(Service service)
	{
		serviceMapper.createService(service);
		return service;
	}

	@Transactional(rollbackFor = Exception.class)
	public List<Service> createUserServices(long userId, List<Service> services)
	{
		serviceMapper.createUserServices(userId, services);
		return services;
	}

	@Transactional(readOnly = true)
	public Service getServiceById(long id)
	{
		return serviceMapper.getServiceById(id);
	}

	@Transactional(readOnly = true)
	public List<Service> getAllServices()
	{
		return serviceMapper.getAllServices();
	}

	@Transactional(readOnly = true)
	public List<Service> getServicesByUserId(long userId)
	{
		return serviceMapper.getServicesByUserId(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Service updateService(Service service)
	{
		serviceMapper.updateService(service);
		return service;
	}

	@Transactional(rollbackFor = Exception.class)
	public List<Service> updateUserServices(long userId, List<Service> services)
	{
		deleteUserServices(userId);
		createUserServices(userId, services);
		return services;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteService(long id)
	{
		serviceMapper.deleteService(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteUserServices(long userId)
	{
		serviceMapper.deleteUserServices(userId);
	}
}
