package com.drones4hire.dronesapp.services.services;

import java.util.List;

import com.drones4hire.dronesapp.models.db.commons.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PilotLicenseMapper;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

@Service
public class PilotLicenseService
{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PilotLicenseMapper pilotLicenseMapper;

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private AWSEmailService emailService;

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
	public PilotLicense updatePilotLicense(PilotLicense license) throws ServiceException
	{
		PilotLicense currLicense = pilotLicenseMapper.getPilotLicenseByUserId(license.getUserId());
		User pilot = userService.getUserById(license.getUserId());
		currLicense.setInsuranceURL(license.getInsuranceURL());
		currLicense.setLicenseURL(license.getLicenseURL());
		if(!currLicense.isVerified() && license.isVerified()) {
			currLicense.setVerified(license.isVerified());
			pilotLicenseMapper.updatePilotLicense(currLicense);
			emailService.sendPilotApprovedEmail(pilot);
		} else {
			currLicense.setVerified(license.isVerified());
			pilotLicenseMapper.updatePilotLicense(currLicense);
		}
		return currLicense;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePilotLicense(long id)
	{
		pilotLicenseMapper.deletePilotLicense(id);
	}
}
