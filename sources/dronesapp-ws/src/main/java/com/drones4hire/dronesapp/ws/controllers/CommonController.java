package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.models.dto.BudgetDTO;
import com.drones4hire.dronesapp.services.services.BudgetService;
import com.drones4hire.dronesapp.services.services.CountryService;
import com.drones4hire.dronesapp.services.services.StateService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Create budget", nickname = "createBudget", code = 201, httpMethod = "POST", response = Budget.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "budgets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Budget createBudget(@Valid @RequestBody BudgetDTO budget)
	{
		return budgetService.createBudget(mapper.map(budget, Budget.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get budget by id", nickname = "getBudgetById", code = 200, httpMethod = "GET", response = Budget.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "budgets/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Budget getBudgetById(
			@ApiParam(value = "Id of the budget", required = true) @PathVariable(value = "id") long id)
	{
		return budgetService.getBudgetById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update budget", nickname = "updateBudget", code = 200, httpMethod = "PUT", response = Budget.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "budgets", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Budget updateBudget(@Valid @RequestBody BudgetDTO budget)
	{
		return budgetService.updateBudget(mapper.map(budget, Budget.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete budget", nickname = "deleteBudget", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "budgets/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBudget(
			@ApiParam(value = "Id of the budget", required = true) @PathVariable(value = "id") long id)
	{
		budgetService.deleteBudget(id);
	}

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
