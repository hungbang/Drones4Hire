package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PaymentService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import com.braintreegateway.Customer;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.AccountDTO;
import com.drones4hire.dronesapp.models.dto.BidDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.BraintreeService;
import com.drones4hire.dronesapp.services.services.PaymentService;
import com.drones4hire.dronesapp.services.services.WalletService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@Api(value = "Payment API")
@CrossOrigin
@RequestMapping("api/v1/payments")
public class PaymentController extends AbstractController
{

	@Autowired
	private PaymentService paymentService;

	@ResponseStatusDetails
	@ApiOperation(value = "Release payment", nickname = "releasePayment", code = 201, httpMethod = "POST", response = Transaction.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(value = "release/{bidId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Transaction releasePayment(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "bidId") long bidId)
			throws ServiceException
	{
		return paymentService.releasePayment(bidId, getPrincipal().getId());
	}
	
	@Autowired
	private WalletService walletService;

	@ResponseStatusDetails
	@ApiOperation(value = "Get client token", nickname = "getClientToken", code = 200, httpMethod = "GET", response = String.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "client_token", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String getClientToken() throws ServiceException
	{
		return paymentService.generateClientToken(getPrincipal().getId());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Create payment token", nickname = "createPaymentToken", code = 201, httpMethod = "POST", response = String.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "checkout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createPaymentToken(@RequestParam(name = "payment_method_nonce", required = true) String paymentMethodNonce) throws ServiceException
	{
		return paymentService.createPaymentToken(getPrincipal().getId(), paymentMethodNonce);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Pay transaction", nickname = "saleTransaction", code = 200, httpMethod = "POST")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody com.braintreegateway.Transaction saleTransaction(@PathVariable("token") String token, @RequestBody BigDecimal amount) throws ServiceException
	{
		Wallet wallet = walletService.getWalletByUserId(getPrincipal().getId());
		return paymentService.saleTransaction(wallet.getPaymentToken(), token, amount);
	}
}
