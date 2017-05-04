package com.drones4hire.dronesapp.ws.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Health check")
@RequestMapping("api/v1/healthcheck")
public class HealthCheckController extends AbstractController {

	@ResponseStatusDetails
	@ApiOperation(value = "Create user", nickname = "createUser", code = 200, httpMethod = "POST",
			notes = "Creates a new user.", response = String.class, responseContainer = "UserType")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createUser()
	{
		return "Drones4Hire is up and running!";
	}
}
