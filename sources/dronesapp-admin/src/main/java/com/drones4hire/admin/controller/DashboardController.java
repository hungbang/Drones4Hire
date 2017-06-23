package com.drones4hire.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.WithdrawSearchCriteria;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ProjectService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.WithdrawService;


@Controller
@RequestMapping("dashboard")
public class DashboardController extends AbstractController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private WithdrawService withdrawService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openDashboardPage()
	{
		return new ModelAndView("dashboard");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "overview", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Integer> getOverview() throws ServiceException
	{
		Map<String, Integer> overview = new HashMap<>();
		
		overview.put("projectsTotal", projectService.getProjectsSearchCount(new ProjectSearchCriteria()));
		overview.put("usersTotal", userService.getUserCount(new UserSearchCriteria()));
		overview.put("pilotsTotal", userService.getUserCount(new UserSearchCriteria(Role.ROLE_PILOT)));
		overview.put("clientsTotal", userService.getUserCount(new UserSearchCriteria(Role.ROLE_CLIENT)));
		overview.put("withdrawRequestsTotal", withdrawService.getWithdrawRequestCount(new WithdrawSearchCriteria()));
		overview.put("polotsToVerifyTotal", 0);
		
		return overview;
	}
}
