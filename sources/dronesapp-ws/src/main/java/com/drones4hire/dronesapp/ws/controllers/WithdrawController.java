package com.drones4hire.dronesapp.ws.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import com.drones4hire.dronesapp.models.dto.WithdrawRequestDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.WithdrawService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Wallet API")
@CrossOrigin
@RequestMapping("api/v1/withdraws")
public class WithdrawController extends AbstractController
{

	@Autowired
	private WithdrawService withdrawService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Create withdraw request", nickname = "createWithdrawRequest", code = 201, httpMethod = "POST", response = WithdrawRequestDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@Secured({ "ROLE_PILOT", "ROLE_CLIENT" })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody WithdrawRequestDTO createWithdraw(@Valid @RequestBody WithdrawRequestDTO wr) throws ServiceException
	{
		WithdrawRequest request = mapper.map(wr, WithdrawRequest.class);
		request.setUserId(getPrincipal().getId());
		return mapper.map(withdrawService.createWithdraw(request), WithdrawRequestDTO.class);
	}
}