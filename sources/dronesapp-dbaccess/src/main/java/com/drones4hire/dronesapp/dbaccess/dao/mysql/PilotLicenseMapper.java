package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.users.PilotLicense;

public interface PilotLicenseMapper
{
	void createPilotLicense(PilotLicense license);

	PilotLicense getPilotLicenseById(long id);
	
	PilotLicense getPilotLicenseByUserId(long userId);

	List<PilotLicense> getAllPilotLicenses();

	void updatePilotLicense(PilotLicense license);

	void deletePilotLicense(long id);
}
