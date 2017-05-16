package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.portfolio.PortfolioItem;
import com.drones4hire.dronesapp.services.services.PortfolioItemService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Api(value = "Portfolio API")
@RequestMapping("api/v1/portfolio")
public class PortfolioItemController extends AbstractController
{

	@Autowired
	private PortfolioItemService portfolioItemService;

	@ResponseStatusDetails
	@ApiOperation(value = "Create portfolio item", nickname = "createPortfolioItem", code = 200, httpMethod = "POST", response = PortfolioItem.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem createPortfolioItem(@RequestBody PortfolioItem portfolioItem)
	{
		return portfolioItemService.createPortfolioItem(portfolioItem);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get portfolio item by id", nickname = "createPortfolioItem", code = 200, httpMethod = "GET", response = PortfolioItem.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem getPortfolioItemById(@ApiParam(value = "Id of the portfolio", required = true) @PathVariable(value="id") long id)
	{
		return portfolioItemService.getPortfolioItemById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all portfolio items", nickname = "getAllPortfolioItems", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PortfolioItem> getAllPortfolioItems()
	{
		return portfolioItemService.getAllPortfolioItems();
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update portfolio item", nickname = "updatePortfolioItem", code = 200, httpMethod = "PUT", response = PortfolioItem.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PortfolioItem updatePortfolioItem(@RequestBody PortfolioItem portfolioItem)
	{
		return portfolioItemService.updatePortfolioItem(portfolioItem);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete portfolio item", nickname = "deletePortfolioItem", code = 200, httpMethod = "DELETE")
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePortfolioItem(@ApiParam(value = "Id of the portfolio", required = true) @PathVariable(value="id") long id)
	{
		portfolioItemService.deletePortfolioItem(id);
	}
}
