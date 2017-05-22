package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.services.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceMapper {

    void createService(Service service);

    void createUserServices(@Param("userId") long userId, @Param("services") List<Service> services);

    Service getServiceById(long id);

    List<Service> getAllServices();

    List<Service> getServicesByUserId(long userId);

    void updateService(Service service);

    void deleteService(long id);

    void deleteUserServices(long userId);

    void deleteServicesByCategoryId(long categoryId);
}
