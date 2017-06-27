package com.drones4hire.dronesapp.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.braintreegateway.CreditCard;
import com.drones4hire.dronesapp.models.dto.CreditCardDTO;
import com.drones4hire.dronesapp.services.services.CreditCardService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Credit card API")
@CrossOrigin
@RequestMapping("api/v1/cards")
public class CreditCardController extends AbstractController
{
	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get user credit cards", nickname = "getUserCreditCards", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	List<CreditCardDTO> getUserCreditCards() throws Exception
	{
		List<CreditCardDTO> result = new ArrayList<>();
		List<CreditCard> creditCards = creditCardService.getCreditCardsByUserId(getPrincipal().getId());
		for (CreditCard creditCard : creditCards)
		{
			result.add(mapper.map(creditCard, CreditCardDTO.class));
		}
		return result;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get credit card by token", nickname = "getCreditCardByToken", code = 200, httpMethod = "GET", response = CreditCardDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO getCreditCardByToken(@PathVariable("token") String token) throws Exception
	{
		return mapper.map(creditCardService.getCreditCardByToken(token), CreditCardDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Create credit card", nickname = "addCreditCard", code = 201, httpMethod = "POST", response = CreditCardDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO addCreditCard(@RequestBody @Valid CreditCardDTO creditCard) throws Exception
	{
		return mapper.map(creditCardService.addCreditCard(getPrincipal().getId(), creditCard.getNumber(),
				creditCard.getExpirationMonth(), creditCard.getExpirationYear(), creditCard.getCvv(), creditCard.getCardholderName()),
				CreditCardDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update credit card", nickname = "updateCreditCard", code = 200, httpMethod = "PUT", response = CreditCardDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO updateCreditCard(@RequestBody @Valid CreditCardDTO creditCard)
			throws Exception
	{
		return mapper.map(creditCardService.updateCreditCard(getPrincipal().getId(), creditCard.getToken(),
				creditCard.getExpirationMonth(), creditCard.getExpirationYear(), creditCard.getCardholderName()), CreditCardDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Make credit card default", nickname = "makeCreditCardDefault", code = 200, httpMethod = "PUT", response = CreditCardDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}/default", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO makeCreditCardDefault(@PathVariable("token") String token) throws Exception
	{
		return mapper.map(creditCardService.makeCreditCardDefault(token), CreditCardDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete credit card", nickname = "deleteCreditCard", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{token}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	void deleteCreditCard(@PathVariable("token") String token) throws Exception
	{
		creditCardService.deleteCreditCard(getPrincipal().getId(), token);
	}
}
