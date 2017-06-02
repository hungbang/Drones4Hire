package com.drones4hire.admin.controller.projects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.admin.controller.AbstractController;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ProjectService;

@Controller
@RequestMapping("projects")
public class ProjectsController extends AbstractController
{
//	@Autowired
//	private ProjectService orderService;
//	
//	@Autowired
//	private AuthenticationService authenticationService;
//
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//	public ModelAndView openOrdersPage() throws ServiceException
//	{
//		return new ModelAndView("orders/index");
//	}
//	
//	@RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody
//	SearchResult<Project> searchOrders(@RequestBody OrderSearchCriteria searchCriteria, @RequestParam(value="egId", required=false) Long egId, @RequestParam(value="cityId", required=false) Long cityId) throws Exception
//	{
//		List<Long> cityIds = new ArrayList<>();
//		if(cityId != null)
//		{
//			cityIds.add(cityId);
//		}
//		else if(egId != null)
//		{
//			for(City city : cityService.getCitiesByEGId(egId))
//			{
//				cityIds.add(city.getId());
//			}
//		}
//		getRoleBasedOrderSearchCriteria(searchCriteria);
//		searchCriteria.setCityIds(cityIds);
//		return orderService.searchOrders(searchCriteria);
//	}
//	
//	@RequestMapping(value = "total", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//	public @ResponseBody String getAllOrdersTotalPrice() throws Exception
//	{
//		return orderService.getAllOrdersTotalPrice().toString();
//	}
//	
//	@RequestMapping(value = "status/count/{delta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody Map<Long, Map<OrderStatus, OrderStatusCount>> getOrderStatusCount(@PathVariable int delta, @RequestParam(value="egId", required=false) Long egId, @RequestParam(value="cityId", required=false) Long cityId) throws Exception
//	{
//		OrderSearchCriteria searchCriteria = getRoleBasedOrderSearchCriteria();
//		searchCriteria.setCityIds(new ArrayList<Long>());
//		if(cityId != null)
//		{
//			searchCriteria.getCityIds().add(cityId);
//		}
//		else if(egId != null)
//		{
//			for(City city : cityService.getCitiesByEGId(egId))
//			{
//				searchCriteria.getCityIds().add(city.getId());
//			}
//		}
//		searchCriteria.setCreatedAtAfter(DateUtils.addDays(new Date(), -delta));
//		searchCriteria.setCreatedAtBefore(DateUtils.addDays(new Date(), 1));
//		return orderStatisticsService.getOrderStatusCount(searchCriteria);
//	}
//	
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "proposals", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//	public ModelAndView openOrderProposalsPage() throws ServiceException
//	{
//		return new ModelAndView("orders/proposals/index");
//	}
//	
//	@RequestMapping(value = "proposals/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody
//	SearchResult<OrderProposalStatistics> searchOrderProposalStatistics(@RequestBody OrderProposalSearchCriteria searchCriteria, @RequestParam(value="egId", required=false) Long egId, @RequestParam(value="cityId", required=false) Long cityId) throws Exception
//	{
//		searchCriteria.setCityIds(getCityIds(egId, cityId));
//		return orderProposalService.searchOrderProposalStatistics(getRoleBasedOrderProposalSearchCriteria(searchCriteria));
//	}
}
