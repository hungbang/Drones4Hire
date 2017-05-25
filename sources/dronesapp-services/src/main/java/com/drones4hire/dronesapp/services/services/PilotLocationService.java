package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PilotLocationMapper;
import com.drones4hire.dronesapp.models.db.users.PilotLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PilotLocationService
{

	@Autowired
	private PilotLocationMapper pilotLocationMapper;

	@Autowired
	private LocationService locationService;

	@Transactional(rollbackFor = Exception.class)
	public PilotLocation createPilotLocation(PilotLocation pilotLocation)
	{
		locationService.createLocation(pilotLocation.getLocation());
		pilotLocationMapper.createPilotLocation(pilotLocation);
		return pilotLocation;
	}

	@Transactional(readOnly = true)
	public PilotLocation getPilotLocationById(long id)
	{
		return pilotLocationMapper.getPilotLocationById(id);
	}

	@Transactional(readOnly = true)
	public List<PilotLocation> getPilotLocationsByUserId(long userId)
	{
		return pilotLocationMapper.getPilotLocationsByUserId(userId);
	}

	@Transactional(readOnly = true)
	public List<PilotLocation> getAllPilotLocations()
	{
		return pilotLocationMapper.getAllPilotLocations();
	}

	@Transactional(rollbackFor = Exception.class)
	public PilotLocation updatePilotLocation(PilotLocation pilotLocation)
	{
		locationService.updateLocation(pilotLocation.getLocation());
		pilotLocationMapper.updatePilotLocation(pilotLocation);
		return pilotLocation;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePilotLocation(PilotLocation pilotLocation)
	{
		pilotLocationMapper.deletePilotLocation(pilotLocation.getId());
		locationService.deleteLocation(pilotLocation.getLocation().getId());
	}
}
