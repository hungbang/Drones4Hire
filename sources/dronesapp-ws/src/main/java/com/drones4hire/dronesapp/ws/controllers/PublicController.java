package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.services.Service;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.AccountDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ServiceService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

@Controller
@Api(value = "Public API")
@CrossOrigin
@RequestMapping("api/v1/public")
public class PublicController extends AbstractController
{

	@Autowired
	private UserService userService;

	@Autowired
	private Mapper mapper;

	@Autowired
	private ServiceService serviceService;

	@ResponseStatusDetails
	@ApiOperation(value = "Get public account by id", nickname = "getPublicAccountById", code = 200, httpMethod = "GET", response = AccountDTO.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "account/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountDTO getPublicAccountById(@ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id) throws
			ServiceException
	{
		User user = userService.getUserById(id);
		if(! user.getRoles().contains(ROLE_PILOT))
		{
			throw new ForbiddenOperationException();
		}
		return mapper.map(user, AccountDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get public account services by id", nickname = "getPublicAccountServicesById", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "account/{id}/services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Service> getPublicAccountServicesById(@ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id)
			throws ServiceException
	{
		User user = userService.getUserById(id);
		if(! user.getRoles().contains(ROLE_PILOT))
		{
			throw new ForbiddenOperationException();
		}
		return serviceService.getServicesByUserId(user.getId());
	}
}
