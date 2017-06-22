package com.drones4hire.dronesapp.ws.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.models.dto.PortfolioItemDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.services.PortfolioService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value = "Portfolio API")
@CrossOrigin
@RequestMapping("api/v1/portfolio")
public class PortfolioController extends AbstractController
{
	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Create portfolio item", nickname = "createPortfolioItem", code = 201, httpMethod = "POST", response = PortfolioItemDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_PILOT"})
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItemDTO createPortfolioItem(@Valid @RequestBody PortfolioItemDTO pi)
	{
		PortfolioItem portfolioItem = mapper.map(pi, PortfolioItem.class);
		portfolioItem.setUserId(getPrincipal().getId());
		return mapper.map(portfolioService.createPortfolioItem(portfolioItem), PortfolioItemDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get portfolio item by id", nickname = "getPortfolioItemById", code = 200, httpMethod = "GET", response = PortfolioItem.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem getPortfolioItemById(
			@ApiParam(value = "Id of the portfolio", required = true) @PathVariable(value = "id") long id)
	{
		return portfolioService.getPortfolioItemById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get user portfolio items", nickname = "getPortfolioItemsByUserId", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PortfolioItemDTO> getPortfolioItemsByUserId(@ApiParam(value = "Id of the pilot", required = true) @RequestParam("id") Long id)
	{
		List<PortfolioItem> portfolioItems = portfolioService.getPortfolioItemsByUserId(id);
		List<PortfolioItemDTO> portfolioItemDTOs = new ArrayList<>();
		for (PortfolioItem portfolioItem : portfolioItems)
		{
			portfolioItemDTOs.add(mapper.map(portfolioItem, PortfolioItemDTO.class));
		}
		return portfolioItemDTOs;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update portfolio item", nickname = "updatePortfolioItem", code = 200, httpMethod = "PUT", response = PortfolioItemDTO.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT"})
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItemDTO updatePortfolioItem(@Valid @RequestBody PortfolioItemDTO pi)
			throws ForbiddenOperationException
	{
		PortfolioItem portfolioItem = portfolioService.getPortfolioItemById(pi.getId());
		checkPrincipalPermissions(portfolioItem.getUserId());
		portfolioItem.setName(pi.getName());
		portfolioItem.setTitle(pi.getTitle());
		portfolioItem.setSummary(pi.getSummary());
		portfolioItem.setType(pi.getType());
		portfolioItem.setItemURL(pi.getItemURL());
		portfolioItem.setServiceCategories(pi.getServiceCategories());
		return mapper.map(portfolioService.updatePortfolioItem(portfolioItem), PortfolioItemDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete portfolio item", nickname = "deletePortfolioItem", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePortfolioItem(
			@ApiParam(value = "Id of the portfolio", required = true) @PathVariable(value = "id") long id)
			throws ForbiddenOperationException
	{
		PortfolioItem portfolioItem = portfolioService.getPortfolioItemById(id);
		checkPrincipalPermissions(portfolioItem.getUserId());
		portfolioService.deletePortfolioItem(id);
	}
}
