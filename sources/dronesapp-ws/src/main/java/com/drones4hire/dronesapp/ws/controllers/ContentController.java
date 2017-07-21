package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.content.Faq;
import com.drones4hire.dronesapp.models.db.content.Policy;
import com.drones4hire.dronesapp.models.db.content.Term;
import com.drones4hire.dronesapp.services.services.FaqService;
import com.drones4hire.dronesapp.services.services.PolicyService;
import com.drones4hire.dronesapp.services.services.TermService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "Content API")
@CrossOrigin
@RequestMapping("api/v1/content")
public class ContentController
{

	@Autowired
	private FaqService faqService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private TermService termService;

	@ResponseStatusDetails
	@ApiOperation(value = "Get FAQ by id", nickname = "getFaqById", code = 200, httpMethod = "GET", response = Faq.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "faqs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Faq getFaqById(
			@ApiParam(value = "Id of the FAQ", required = true) @PathVariable(value = "id") long id)
	{
		return faqService.getFaqById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all FAQs", nickname = "getAllFaqs", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "faqs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Faq> getFaqsByRole(@RequestParam(name = "role", required = true) String role)
	{
		return faqService.getFaqsByRole(role);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get policy by id", nickname = "getPolicyById", code = 200, httpMethod = "GET", response = Policy.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "policy/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Policy getPolicyById(
			@ApiParam(value = "Id of the policy", required = true) @PathVariable(value = "id") long id)
	{
		return policyService.getPolicyById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all policy", nickname = "getAllPolicy", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "policy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Policy> getAllPolicy()
	{
		return policyService.getAllPolicy();
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get term by id", nickname = "getTermById", code = 200, httpMethod = "GET", response = Term.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "terms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Term getTermById(
			@ApiParam(value = "Id of the term", required = true) @PathVariable(value = "id") long id)
	{
		return termService.getTermById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all terms", nickname = "getAllTerms", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "terms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Term> getAllTerms()
	{
		return termService.getAllTerms();
	}
}
