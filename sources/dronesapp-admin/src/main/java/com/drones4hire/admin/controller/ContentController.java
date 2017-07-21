package com.drones4hire.admin.controller;

import com.drones4hire.dronesapp.models.db.content.Faq;
import com.drones4hire.dronesapp.models.db.content.Policy;
import com.drones4hire.dronesapp.models.db.content.Term;
import com.drones4hire.dronesapp.services.services.FaqService;
import com.drones4hire.dronesapp.services.services.PolicyService;
import com.drones4hire.dronesapp.services.services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("content")
public class ContentController extends AbstractController
{

	@Autowired
	private FaqService faqService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private TermService termService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "faqs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelAndView getFaqPage()
	{
		return new ModelAndView("/content/faqs/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "policy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelAndView getPolicyPage()
	{
		return new ModelAndView("/content/policy/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "terms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ModelAndView getTermsPage()
	{
		return new ModelAndView("/content/terms/index");
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "faqs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Faq createFaq(@RequestBody Faq faq)
	{
		return faqService.createFaq(faq);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "faqs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Faq getFaqById(@PathVariable(value = "id") long id)
	{
		return faqService.getFaqById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "faqs/{role}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Faq> getAllFaqs(String role)
	{
		return faqService.getFaqsByRole(role);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "faqs", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Faq updateFaq(@RequestBody Faq faq)
	{
		return faqService.updateFaq(faq);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "faqs/{id}", method = RequestMethod.DELETE)
	public void deleteFaq(@PathVariable(value = "id") long id)
	{
		faqService.deleteFaq(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "policy", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Policy createPolicy(@RequestBody Policy policy)
	{
		return policyService.createPolicy(policy);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "policy/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Policy getPolicyById(long id)
	{
		return policyService.getPolicyById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "policy/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Policy> getAllPolicy()
	{
		return policyService.getAllPolicy();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "policy", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Policy updatePolicy(@RequestBody Policy policy)
	{
		return policyService.updatePolicy(policy);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "policy/{id}", method = RequestMethod.DELETE)
	public void deletePolicy(@PathVariable(value = "id") long id)
	{
		policyService.deletePolicy(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "terms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Term createTerm(@RequestBody Term term)
	{
		return termService.createTerm(term);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "terms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Term getTermById(long id)
	{
		return termService.getTermById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "terms/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Term> getAllTerms()
	{
		return termService.getAllTerms();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "terms", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Term updateTerm(@RequestBody Term term)
	{
		return termService.updateTerm(term);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "terms/{id}", method = RequestMethod.DELETE)
	public void deleteTerm(@PathVariable(value = "id") long id)
	{
		termService.deleteTerm(id);
	}
}
