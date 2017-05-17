package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.models.dto.PortfolioItemDTO;
import com.drones4hire.dronesapp.services.services.PortfolioService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

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
	@ApiOperation(value = "Create portfolio item", nickname = "createPortfolioItem", code = 201, httpMethod = "POST", response = PortfolioItem.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem createPortfolioItem(@Valid @RequestBody PortfolioItemDTO pi)
	{
		PortfolioItem portfolioItem = mapper.map(pi, PortfolioItem.class);
		portfolioItem.setUserId(getPrincipal().getId());
		return portfolioService.createPortfolioItem(portfolioItem);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get portfolio item by id", nickname = "createPortfolioItem", code = 200, httpMethod = "GET", response = PortfolioItem.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem getPortfolioItemById(@ApiParam(value = "Id of the portfolio", required = true) @PathVariable(value="id") long id)
	{
		return portfolioService.getPortfolioItemById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get user portfolio items", nickname = "getUserPortfolioItems", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PortfolioItem> getUserPortfolioItems()
	{
		return portfolioService.getPortfolioItemsByUserId(getPrincipal().getId());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update portfolio item", nickname = "updatePortfolioItem", code = 200, httpMethod = "PUT", response = PortfolioItem.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem updatePortfolioItem(@RequestBody PortfolioItem portfolioItem)
	{
		return portfolioService.updatePortfolioItem(portfolioItem);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete portfolio item", nickname = "deletePortfolioItem", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePortfolioItem(@ApiParam(value = "Id of the portfolio", required = true) @PathVariable(value="id") long id)
	{
		portfolioService.deletePortfolioItem(id);
	}
}
