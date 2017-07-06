package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.payments.ServiceFee;

import java.util.List;

public interface ServiceFeeMapper
{

	void createServiceFee(ServiceFee serviceFee);

	ServiceFee getServiceFeeById(long id);

	List<ServiceFee> getAllServiceFees();

	void updateServiceFee(ServiceFee serviceFee);

	void deleteServiceFee(long id);
}
