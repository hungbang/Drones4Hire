package com.drones4hire.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.CountryService;
import com.drones4hire.dronesapp.services.services.LocationService;
import com.drones4hire.dronesapp.services.services.StateService;

@Controller
@RequestMapping("locations")
public class LocationsController extends AbstractController
{
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CountryService countryService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "states", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<State> getStates() throws ServiceException
	{
		return stateService.getAllStates();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Country> getCountries() throws ServiceException
	{
		return countryService.getAllCountries();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Location updateLocation(@RequestBody Location location, @PathVariable long id) throws ServiceException
	{
		Location loc = locationService.getLocationById(id);
		loc.setAddress(location.getAddress());
		loc.setCity(location.getCity());
		loc.setCountry(location.getCountry());
		loc.setCoordinates(location.getCoordinates());
		loc.setState(location.getState());
		loc.setPostcode(location.getPostcode());
		return locationService.updateLocation(loc);
	}
}
