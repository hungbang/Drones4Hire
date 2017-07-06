package com.drones4hire.admin.controller;

import java.util.List;

import com.drones4hire.dronesapp.models.db.payments.ServiceFee;
import com.drones4hire.dronesapp.services.services.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.services.Service;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("common")
public class CommonController
{

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private ServiceCategoryService serviceCategoryService;

	@Autowired
	private DurationService durationService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private ServiceFeeService serviceFeeService;

	@Autowired
	private PaidOptionService paidOptionService;

	@Autowired
	private Mapper mapper;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "budgets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Budget> getAllBudgets()
	{
		return budgetService.getAllBudgets();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Service> getAllServices()
	{
		return serviceService.getAllServices();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceCategory> getAllCategories()
	{
		return serviceCategoryService.getAllServiceCategories();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "durations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Duration> getAllDurations()
	{
		return durationService.getAllDurations();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "paidOptions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PaidOption> getAllPaidOptions()
	{
		return paidOptionService.getAllPaidOptions();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/fees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceFee createServiceFee(@Valid @RequestBody ServiceFee serviceFee)
	{
		return serviceFeeService.createServiceFee(mapper.map(serviceFee, ServiceFee.class));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/fees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelAndView getServiceFeesPage()
	{
		return new ModelAndView("services/fees/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/fees/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceFee> getServiceFees()
	{
		return serviceFeeService.getAllServiceFees();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/fees", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceFee updateServiceFee(@Valid @RequestBody ServiceFee serviceFee)
	{
		return serviceFeeService.updateServiceFee(mapper.map(serviceFee, ServiceFee.class));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services/fees/{id}", method = RequestMethod.DELETE)
	public void deleteServiceFee(@PathVariable(value = "id") Long id)
	{
		serviceFeeService.deleteServiceFee(id);
	}
}
