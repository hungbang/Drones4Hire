package com.drones4hire.dronesapp.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PayoneerService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Payoneer Callback API")
@CrossOrigin
@RequestMapping("api/v1/payoneer")
public class PayoneerCallbackController extends AbstractController
{
	
	@Autowired
	private PayoneerService payoneerService;

	@ResponseStatusDetails
	@ApiOperation(value = "Approve payonner account", nickname = "approvePayoneerAccount", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "approve", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void approvePayoneerAccount(
			@RequestParam(value = "App UID", required = true) @PathVariable(value = "apuid") String apuid,
			@RequestParam(value = "Payoneer Id", required = true) @PathVariable(value = "payoneerid") String payoneerId,
			@RequestParam(value = "Session Id", required = false) @PathVariable(value = "sessionid") String sessionId)
			throws ServiceException
	{
		payoneerService.approvePayoneerAccount(payoneerId);
	}
}
