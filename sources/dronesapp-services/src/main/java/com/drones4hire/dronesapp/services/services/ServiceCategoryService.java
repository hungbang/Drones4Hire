package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ServiceCategoryMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ServiceMapper;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceCategoryService
{

	@Autowired
	private ServiceCategoryMapper serviceCategoryMapper;

	@Autowired
	private ServiceMapper serviceMapper;

	@Transactional(rollbackFor = Exception.class)
	public ServiceCategory createServiceCategory(ServiceCategory serviceCategory)
	{
		serviceCategoryMapper.createServiceCategory(serviceCategory);
		return serviceCategory;
	}

	@Transactional(readOnly = true)
	public ServiceCategory getServiceCategoryById(long id)
	{
		return serviceCategoryMapper.getServiceCategoryById(id);
	}

	@Transactional(readOnly = true)
	public List<ServiceCategory> getAllServiceCategories()
	{
		return serviceCategoryMapper.getAllServiceCategories();
	}

	@Transactional(rollbackFor = Exception.class)
	public ServiceCategory updateServiceCategory(ServiceCategory serviceCategory)
	{
		serviceCategoryMapper.updateServiceCategory(serviceCategory);
		return serviceCategory;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteServiceCategory(long id)
	{
		serviceMapper.deleteServicesByCategoryId(id);
		serviceCategoryMapper.deleteServiceCategory(id);
	}
}
