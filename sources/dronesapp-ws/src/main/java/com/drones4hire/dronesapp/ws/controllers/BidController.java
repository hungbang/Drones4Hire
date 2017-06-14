package com.drones4hire.dronesapp.ws.controllers;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.dto.BidDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.BidService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value = "Bids API")
@CrossOrigin
@RequestMapping("api/v1/bids")
public class BidController extends AbstractController
{
	@Autowired
	private BidService bidService;

	@Autowired
	private Mapper mapper;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Create bid", nickname = "createBid", code = 201, httpMethod = "POST", response = BidDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_PILOT"})
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidDTO createBid(@Valid @RequestBody BidDTO bidDTO) throws ServiceException
	{
		Bid bid = mapper.map(bidDTO, Bid.class);
		return mapper.map(bidService.createBid(bid, getPrincipal().getId()), BidDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update bid", nickname = "updateBid", code = 201, httpMethod = "PUT", response = BidDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT"})
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidDTO updateBid(@Valid @RequestBody BidDTO bidDTO) throws ServiceException
	{
		Bid result = bidService.updateBid(mapper.map(bidDTO, Bid.class), getPrincipal().getId());
		return mapper.map(result, BidDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Retract bid", nickname = "deleteBid", code = 201, httpMethod = "DELETE")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{id}/retract", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBid(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "id") long bidId) throws ServiceException
	{
		bidService.deleteBid(bidId, getPrincipal().getId());
	}
	

	@ResponseStatusDetails
	@ApiOperation(value = "Award bid", nickname = "awardBid", code = 201, httpMethod = "POST", response = BidDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(value = "{id}/award", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidDTO awardBid(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "id") long bidId) throws ServiceException
	{
		return mapper.map(bidService.awardBid(bidId, getPrincipal().getId()), BidDTO.class);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Rewoke bid", nickname = "rewokeBid", code = 201, httpMethod = "POST", response = BidDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(value = "{id}/rewoke", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidDTO rewokeBid(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "id") long bidId) throws ServiceException
	{
		return mapper.map(bidService.rewokeBid(bidId, getPrincipal().getId()), BidDTO.class);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Accept bid", nickname = "acceptBid", code = 201, httpMethod = "POST", response = BidDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_PILOT"})
	@RequestMapping(value = "{id}/accept", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidDTO acceptBid(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "id") long bidId) throws ServiceException
	{
		return mapper.map(bidService.acceptBid(bidId, getPrincipal().getId()), BidDTO.class);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Reject bid", nickname = "rejectBid", code = 201, httpMethod = "POST", response = BidDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_PILOT"})
	@RequestMapping(value = "{id}/reject", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidDTO rejectBid(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "id") long bidId) throws ServiceException
	{
		return mapper.map(bidService.rejectBid(bidId, getPrincipal().getId()), BidDTO.class);
	}
}
