package com.drones4hire.dronesapp.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PayoneerService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
			@ApiParam(value = "App UID", required = true) @RequestParam(value = "apuid") String apuid,
			@ApiParam(value = "Payoneer Id", required = true) @RequestParam(value = "payoneerid") String payoneerId)
			throws ServiceException
	{
		payoneerService.approvePayoneerAccount(apuid);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Accept payment request", nickname = "acceptPayoneerAccount", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "accept", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void acceptPayoneerRequest(
			@ApiParam(value = "App UID", required = true) @RequestParam(value = "apuid") String apuid,
			@ApiParam(value = "Payment Id", required = true) @RequestParam(value = "IntPaymentId") Long paymentId,
			@ApiParam(value = "Payoneer payment Id", required = true) @RequestParam(value = "PayoneerPaymentId") String PayoneerPaymentId)
			throws ServiceException
	{
		payoneerService.acceptPayment(apuid, paymentId);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Accept payment request", nickname = "acceptPayoneerAccount", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "cancel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cancelPayoneerRequest(
			@ApiParam(value = "App UID", required = true) @RequestParam(value = "apuid") String apuid,
			@ApiParam(value = "Payment Id", required = true) @RequestParam(value = "IntPaymentId") Long paymentId,
			@ApiParam(value = "Payoneer payment Id", required = true) @RequestParam(value = "PayoneerPaymentId") String payoneerPaymentId)
			throws ServiceException
	{
		payoneerService.cancelPayment(apuid, paymentId);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Money funded", nickname = "funedPayoneerRequest", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "cancel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void funedPayoneerRequest(
			@ApiParam(value = "App UID", required = true) @RequestParam(value = "apuid") String apuid,
			@ApiParam(value = "Payment Id", required = true) @RequestParam(value = "IntPaymentId") Long paymentId,
			@ApiParam(value = "Payment Id", required = true) @RequestParam(value = "PaymentId") String payoneerPaymentId)
			throws ServiceException
	{
		payoneerService.fund(apuid, paymentId);
	}
}
