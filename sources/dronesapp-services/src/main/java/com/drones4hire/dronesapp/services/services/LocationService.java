package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.LocationMapper;
import com.drones4hire.dronesapp.models.db.commons.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService
{
	@Autowired
	private LocationMapper locationMapper;

	@Transactional(rollbackFor = Exception.class)
	public Location createLocation(Location location)
	{
		locationMapper.createLocation(location);
		return location;
	}
	
	@Transactional(readOnly = true)
	public Location getLocationById(long id)
	{
		return locationMapper.getLocationById(id);
	}

	@Transactional(readOnly = true)
	public List<Location> getAllLocations()
	{
		return locationMapper.getAllLocations();
	}

	@Transactional(rollbackFor = Exception.class)
	public Location updateLocation(Location location)
	{
		locationMapper.updateLocation(location);
		return getLocationById(location.getId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteLocation(long id)
	{
		locationMapper.deleteLocation(id);
	}
}