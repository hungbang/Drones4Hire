package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.PaidOption;

public interface PaidOptionMapper
{
	void createPaidOption(PaidOption device);

	PaidOption getPaidOptionById(long id);

	List<PaidOption> getAllPaidOptions();

	void updatePaidOption(PaidOption device);

	void deletePaidOption(long id);
}
