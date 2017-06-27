package com.drones4hire.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.BidService;

@Controller
@RequestMapping("bids")
public class BidController extends AbstractController
{
	@Autowired
	private BidService bidService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Bid createBid(@Valid @RequestBody Bid bid) throws ServiceException
	{
		return bidService.createBid(bid, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Bid updateBid(@Valid @RequestBody Bid bid) throws ServiceException
	{
		return bidService.updateBid(bid, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{id}/retract", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBid(@PathVariable(value = "id") long bidId) throws ServiceException
	{
		bidService.deleteBid(bidId, getPrincipal().getId());
	}

//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(value = "{id}/award", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody Bid awardBid(@PathVariable(value = "id") long bidId) throws ServiceException
//	{
//		return bidService.awardBid(bidId, getPrincipal().getId());
//	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "{id}/rewoke", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Bid rewokeBid(@PathVariable(value = "id") long bidId) throws ServiceException
	{
		return bidService.revokeBid(bidId, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "{id}/accept", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Bid acceptBid(@PathVariable(value = "id") long bidId) throws ServiceException
	{
		return bidService.acceptBid(bidId, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "{id}/reject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Bid rejectBid(@PathVariable(value = "id") long bidId) throws ServiceException
	{
		return bidService.rejectBid(bidId, getPrincipal().getId());
	}
}
