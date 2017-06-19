package com.drones4hire.admin.controller;

import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.services.ServiceCategory;
import com.drones4hire.dronesapp.services.services.BudgetService;
import com.drones4hire.dronesapp.services.services.DurationService;
import com.drones4hire.dronesapp.services.services.PaidOptionService;
import com.drones4hire.dronesapp.services.services.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

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
	private PaidOptionService paidOptionService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "budgets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Budget> getAllBudgets()
	{
		return budgetService.getAllBudgets();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ServiceCategory> getAllServiceCategories()
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
}
