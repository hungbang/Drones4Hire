package com.drones4hire.dronesapp.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.dto.PaymentClientDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PaymentService;
import com.drones4hire.dronesapp.services.services.WalletService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Payment API")
@CrossOrigin
@RequestMapping("api/v1/payments")
public class PaymentController extends AbstractController
{
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private WalletService walletService;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Get client token", nickname = "getClientToken", code = 200, httpMethod = "GET", response = String.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaymentClientDTO getClientToken() throws ServiceException
	{
		Wallet wallet = walletService.getNotNullUserWallet(getPrincipal().getId());
		return new PaymentClientDTO(paymentService.generateClientToken(wallet.getPaymentToken()));
	}
}
