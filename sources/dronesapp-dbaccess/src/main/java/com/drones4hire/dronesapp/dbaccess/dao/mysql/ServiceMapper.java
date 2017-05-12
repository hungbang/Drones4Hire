package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.services.Service;

import java.util.List;

public interface ServiceMapper {

    void create(Service service);

    Service getServiceById(long id);

    List<Service> getAllServices();

    void updateService(Service service);

    void deleteService(long id);
}
