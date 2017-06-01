package com.drones4hire.dronesapp.ws.controllers;

import com.braintreegateway.CreditCard;
import com.drones4hire.dronesapp.models.dto.CreditCardDTO;
import com.drones4hire.dronesapp.services.services.CreditCardService;
import io.swagger.annotations.Api;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO getCreditCardByToken(@PathVariable("token") String token) throws Exception
	{
		return mapper.map(creditCardService.getCreditCardByToken(token), CreditCardDTO.class);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO addCreditCard(@RequestBody @Valid CreditCardDTO creditCard) throws Exception
	{
		return mapper.map(creditCardService.addCreditCard(getPrincipal().getId(), creditCard.getNumber(),
				creditCard.getExpirationMonth(), creditCard.getExpirationYear(), creditCard.getCvv()),
				CreditCardDTO.class);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO updateCreditCard(@PathVariable("token") String token, @RequestBody @Valid CreditCardDTO creditCard)
			throws Exception
	{
		return mapper.map(creditCardService.updateCreditCard(getPrincipal().getId(), token,
				creditCard.getExpirationMonth(), creditCard.getExpirationYear()), CreditCardDTO.class);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}/default", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	CreditCardDTO makeCreditCardDefault(@PathVariable("token") String token) throws Exception
	{
		return mapper.map(creditCardService.makeCreditCardDefault(token), CreditCardDTO.class);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{token}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	void deleteCreditCard(@PathVariable("token") String token) throws Exception
	{
		creditCardService.deleteCreditCard(getPrincipal().getId(), token);
	}
}
