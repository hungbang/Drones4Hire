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
	public @ResponseBody BidDTO createBid(@Valid @RequestBody BidDTO bidDTO)
	{
		Bid bid = mapper.map(bidDTO, Bid.class);
		bid.setUserId(getPrincipal().getId());
		return mapper.map(bidService.createBid(bid), BidDTO.class);
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
		Bid bid = bidService.getBidById(bidDTO.getId());
		Bid result = bidService.updateBid(mapper.map(bidDTO, Bid.class));
		return mapper.map(result, BidDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Retract bid", nickname = "deleteBid", code = 201, httpMethod = "DELETE")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteBid(@ApiParam(value = "Id of the bid", required = true) @PathVariable(value = "id") long id) throws ServiceException
	{
		Bid bid = bidService.getBidById(id);
		bidService.deleteBid(id);
	}
}
