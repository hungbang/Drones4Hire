package com.drones4hire.dronesapp.services.services;

import java.util.List;

import com.drones4hire.dronesapp.models.db.commons.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PilotLicenseMapper;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.User;

@Service
public class PilotLicenseService
{
	@Autowired
	private PilotLicenseMapper pilotLicenseMapper;

	@Autowired
	private CountryService countryService;

	@Transactional(rollbackFor = Exception.class)
	public PilotLicense createPilotLicense(PilotLicense pilotLicense)
	{
		pilotLicenseMapper.createPilotLicense(pilotLicense);
		return pilotLicense;
	}

	@Transactional(rollbackFor = Exception.class)
	public PilotLicense createDefaultPilotLicense(User user)
	{
		PilotLicense pilotLicense = new PilotLicense(user.getId());
		Country country = countryService.getCountryById(user.getLocation().getCountry().getId());
		if(country.isLicenseRequired())
		{
			pilotLicense.setVerified(false);
		} else
		{
			pilotLicense.setVerified(true);
		}
		return createPilotLicense(pilotLicense);
	}
	
	@Transactional(readOnly = true)
	public PilotLicense getPilotLicenseById(long id)
	{
		return pilotLicenseMapper.getPilotLicenseById(id);
	}
	
	@Transactional(readOnly = true)
	public PilotLicense getPilotLicenseByUserId(long userId)
	{
		return pilotLicenseMapper.getPilotLicenseByUserId(userId);
	}

	@Transactional(readOnly = true)
	public List<PilotLicense> getAllPilotLicenses()
	{
		return pilotLicenseMapper.getAllPilotLicenses();
	}

	@Transactional(rollbackFor = Exception.class)
	public PilotLicense updatePilotLicense(PilotLicense pilotLicense)
	{
		pilotLicenseMapper.updatePilotLicense(pilotLicense);
		return pilotLicense;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePilotLicense(long id)
	{
		pilotLicenseMapper.deletePilotLicense(id);
	}
}
