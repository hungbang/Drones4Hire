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
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PaidOptionService;

@Controller
@RequestMapping("paidoptions")
public class PaidOptionController extends AbstractController
{
	@Autowired
	private PaidOptionService paidOptionService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openPilotsPage() throws ServiceException
	{
		return new ModelAndView("paidoption/index");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PaidOption> getPaidOptionsList() throws ServiceException
	{
		return paidOptionService.getAllPaidOptions();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption getPaidOption(@PathVariable long id) throws ServiceException
	{
		return paidOptionService.getPaidOptionById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption updatePaidOption(@RequestBody PaidOption option, @PathVariable long id) throws ServiceException
	{
		PaidOption currOption = paidOptionService.getPaidOptionById(id);
		currOption.setCurrency(option.getCurrency());
		currOption.setDescription(option.getDescription());
		currOption.setPrice(option.getPrice());
		currOption.setTitle(option.getTitle());
		paidOptionService.updatePaidOption(currOption);
		return currOption;
	}

}
