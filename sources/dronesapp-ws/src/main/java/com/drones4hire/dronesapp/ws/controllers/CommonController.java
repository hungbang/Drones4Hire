package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.services.services.CountryService;
import com.drones4hire.dronesapp.services.services.StateService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "Common API")
@RequestMapping("api/v1/common")
public class CommonController extends AbstractController
{

	@Autowired
	private CountryService countryService;

	@Autowired
	private StateService stateService;

	@ResponseStatusDetails
	@ApiOperation(value = "Get countries", nickname = "getCountries", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Country> getCountries()
	{
		return countryService.getAllCountries();
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get states", nickname = "getStates", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "states", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<State> getStates()
	{
		return stateService.getAllStates();
	}
}
