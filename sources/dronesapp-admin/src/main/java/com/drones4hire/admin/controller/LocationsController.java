package com.drones4hire.admin.controller;

import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.CountrySearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
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
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value = "states/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<State> getStates() throws ServiceException
	{
		return stateService.getAllStates();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "countries", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView getCountriesPage()
	{
		return new ModelAndView("countries/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "countries/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<Country> searchCountries(@RequestBody CountrySearchCriteria sc)
	{
		return countryService.search(sc);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "countries/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Country> getCountries() throws ServiceException
	{
		return countryService.getAllCountries();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "countries", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Country updateCountry(@RequestBody Country country) throws ServiceException
	{
		return countryService.updateCountry(country);
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
