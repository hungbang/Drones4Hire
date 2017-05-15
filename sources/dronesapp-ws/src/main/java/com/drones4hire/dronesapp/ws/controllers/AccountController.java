package com.drones4hire.dronesapp.ws.controllers;

import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Account API")
@CrossOrigin
@RequestMapping("api/v1/account")
public class AccountController extends AbstractController
{
	@Autowired
	private UserService userService;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Get user account", nickname = "getUserAccount", code = 200, httpMethod = "GET", response = User.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUserAccount() throws MappingException, ServiceException
	{
		return userService.getUserById(getPrincipal().getId());
	}
}
