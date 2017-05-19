package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.dto.BudgetDTO;
import com.drones4hire.dronesapp.models.dto.DurationDTO;
import com.drones4hire.dronesapp.models.dto.PaidOptionDTO;
import com.drones4hire.dronesapp.services.services.*;
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
	private DurationService durationService;

	@Autowired
	private PaidOptionService paidOptionService;

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
	@ApiOperation(value = "Get all budgets", nickname = "getAllBudgets", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "budgets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Budget> getAllBudgets()
	{
		return budgetService.getAllBudgets();
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
	@ApiOperation(value = "Create duration", nickname = "createDuration", code = 201, httpMethod = "POST", response = Duration.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "durations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Duration createDuration(@Valid @RequestBody DurationDTO duration)
	{
		return durationService.createDuration(mapper.map(duration, Duration.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get duration by id", nickname = "getDurationById", code = 200, httpMethod = "GET", response = Duration.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "durations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Duration getDurationById(
			@ApiParam(value = "Id of the duration", required = true) @PathVariable(value = "id") long id)
	{
		return durationService.getDurationById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all durations", nickname = "getAllDurations", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "durations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Duration> getAllDurations()
	{
		return durationService.getAllDurations();
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update duration", nickname = "updateDuration", code = 200, httpMethod = "PUT", response = Duration.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "durations", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Duration updateDuration(@Valid @RequestBody DurationDTO duration)
	{
		return durationService.updateDuration(mapper.map(duration, Duration.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete duration", nickname = "deleteDuration", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "durations/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteDuration(
			@ApiParam(value = "Id of the duration", required = true) @PathVariable(value = "id") long id)
	{
		durationService.deleteDuration(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Create paid option", nickname = "createPaidOption", code = 201, httpMethod = "POST", response = PaidOption.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "paidoptions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption createPaidOption(@Valid @RequestBody PaidOptionDTO paidOption)
	{
		return paidOptionService.createPaidOption(mapper.map(paidOption, PaidOption.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get paid option by id", nickname = "getPaidOptionById", code = 200, httpMethod = "GET", response = PaidOption.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "paidoptions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption getPaidOptionById(
			@ApiParam(value = "Id of the paid option", required = true) @PathVariable(value = "id") long id)
	{
		return paidOptionService.getPaidOptionById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all paid options", nickname = "getAllPaidOptions", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "paidoptions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PaidOption> getAllPaidOptions()
	{
		return paidOptionService.getAllPaidOptions();
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update paid option", nickname = "updatePaidOption", code = 200, httpMethod = "PUT", response = PaidOption.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "paidoptions", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption updatePaidOption(@Valid @RequestBody PaidOptionDTO paidOption)
	{
		return paidOptionService.updatePaidOption(mapper.map(paidOption, PaidOption.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete paid option", nickname = "deletePaidOption", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "paidoptions/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePaidOption(
			@ApiParam(value = "Id of the paid option", required = true) @PathVariable(value = "id") long id)
	{
		paidOptionService.deletePaidOption(id);
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
