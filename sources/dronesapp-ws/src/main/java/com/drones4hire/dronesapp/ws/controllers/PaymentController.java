package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PaymentService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Api(value = "Payment API")
@CrossOrigin
@RequestMapping("api/v1/payments")
public class PaymentController extends AbstractController
{

	@Autowired
	private PaymentService paymentService;

	@ResponseStatusDetails
	@ApiOperation(value = "Release payment", nickname = "releasePayment", code = 201, httpMethod = "POST")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(value = "{bidId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void releasePayment(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "bidId") long bidId)
			throws ServiceException
	{
		paymentService.releasePayment(bidId, getPrincipal().getId());
	}
}
