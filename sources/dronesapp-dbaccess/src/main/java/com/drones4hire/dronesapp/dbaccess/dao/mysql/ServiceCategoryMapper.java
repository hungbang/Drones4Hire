package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.services.ServiceCategory;

import java.util.List;

public interface ServiceCategoryMapper {

    void createServiceCategory(ServiceCategory serviceCategory);

    ServiceCategory getServiceCategoryById(long id);

    List<ServiceCategory> getAllServiceCategories();

    void updateServiceCategory(ServiceCategory serviceCategory);

    void deleteServiceCategory(long id);
}
