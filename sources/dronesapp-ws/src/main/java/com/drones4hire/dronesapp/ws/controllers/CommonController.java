package com.drones4hire.dronesapp.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.models.db.services.Service;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;
import com.drones4hire.dronesapp.models.dto.BudgetDTO;
import com.drones4hire.dronesapp.models.dto.DurationDTO;
import com.drones4hire.dronesapp.models.dto.FeesDTO;
import com.drones4hire.dronesapp.services.services.BudgetService;
import com.drones4hire.dronesapp.services.services.CountryService;
import com.drones4hire.dronesapp.services.services.DurationService;
import com.drones4hire.dronesapp.services.services.ProjectService;
import com.drones4hire.dronesapp.services.services.ServiceCategoryService;
import com.drones4hire.dronesapp.services.services.ServiceService;
import com.drones4hire.dronesapp.services.services.StateService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	private ServiceService serviceService;

	@Autowired
	private ServiceCategoryService serviceCategoryService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private Mapper mapper;

	/**
	 * --------------- Budgets API ---------------
	 */
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
	@ApiOperation(value = "Create budget (admin)", nickname = "createBudget", code = 201, httpMethod = "POST", response = Budget.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "budgets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Budget createBudget(@Valid @RequestBody BudgetDTO budget)
	{
		return budgetService.createBudget(mapper.map(budget, Budget.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update budget (admin)", nickname = "updateBudget", code = 200, httpMethod = "PUT", response = Budget.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "budgets", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Budget updateBudget(@Valid @RequestBody BudgetDTO budget)
	{
		return budgetService.updateBudget(mapper.map(budget, Budget.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete budget (admin)", nickname = "deleteBudget", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "budgets/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBudget(
			@ApiParam(value = "Id of the budget", required = true) @PathVariable(value = "id") long id)
	{
		budgetService.deleteBudget(id);
	}


	/**
	 * --------------- Durations API ---------------
	 */
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
	@ApiOperation(value = "Create duration (admin)", nickname = "createDuration", code = 201, httpMethod = "POST", response = Duration.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "durations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Duration createDuration(@Valid @RequestBody DurationDTO duration)
	{
		return durationService.createDuration(mapper.map(duration, Duration.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update duration (admin)", nickname = "updateDuration", code = 200, httpMethod = "PUT", response = Duration.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "durations", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Duration updateDuration(@Valid @RequestBody DurationDTO duration)
	{
		return durationService.updateDuration(mapper.map(duration, Duration.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete duration (admin)", nickname = "deleteDuration", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "durations/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteDuration(
			@ApiParam(value = "Id of the duration", required = true) @PathVariable(value = "id") long id)
	{
		durationService.deleteDuration(id);
	}

	
	/**
	 * --------------- Services API ---------------
	 */
	@ResponseStatusDetails
	@ApiOperation(value = "Get service by id", nickname = "getServiceById", code = 200, httpMethod = "GET", response = Service.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Service getServiceById(
			@ApiParam(value = "Id of the service", required = true) @PathVariable(value = "id") long id)
	{
		return serviceService.getServiceById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all services", nickname = "getAllServices", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Service> getAllServices()
	{
		return serviceService.getAllServices();
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Create service (admin)", nickname = "createService", code = 201, httpMethod = "POST", response = Service.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "services", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Service createService(@RequestBody Service service)
	{
		return serviceService.createService(service);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update service (admin)", nickname = "updateService", code = 200, httpMethod = "PUT", response = Service.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "services", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Service updateService(@RequestBody Service service)
	{
		return serviceService.updateService(service);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete service (admin)", nickname = "deleteService", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "services/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteService(
			@ApiParam(value = "Id of the service", required = true) @PathVariable(value = "id") long id)
	{
		serviceService.deleteService(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get service category by id", nickname = "getServiceCategoryById", code = 200, httpMethod = "GET", response = ServiceCategory.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceCategory getServiceCategoryById(
			@ApiParam(value = "Id of the serviceCategory group", required = true) @PathVariable(value = "id") long id)
	{
		return serviceCategoryService.getServiceCategoryById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all service categories", nickname = "getAllServiceCategories", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceCategory> getAllServiceCategories()
	{
		return serviceCategoryService.getAllServiceCategories();
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Create service category (admin)", nickname = "createServiceCategory", code = 201, httpMethod = "POST", response = ServiceCategory.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "services/categories", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceCategory createServiceCategory(@RequestBody ServiceCategory serviceCategory)
	{
		return serviceCategoryService.createServiceCategory(serviceCategory);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update service category (admin)", nickname = "updateServiceCategory", code = 200, httpMethod = "PUT", response = ServiceCategory.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "services/categories", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceCategory updateServiceCategory(@RequestBody ServiceCategory serviceCategory)
	{
		return serviceCategoryService.updateServiceCategory(serviceCategory);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete service category (admin)", nickname = "deleteServiceCategory", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "services/categories/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteServiceCategory(
			@ApiParam(value = "Id of the serviceCategory group", required = true) @PathVariable(value = "id") long id)
	{
		serviceCategoryService.deleteServiceCategory(id);
	}

	/**
	 * --------------- Countries/States API ---------------
	 */
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
	
	/**
	 * --------------- Fee API ---------------
	 */
	@ResponseStatusDetails
	@ApiOperation(value = "Get fees", nickname = "getFees", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "fees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FeesDTO getFees()
	{
		return new FeesDTO(projectService.getServiceFee());
	}
}