package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.Message;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.MessageService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "Messages API")
@CrossOrigin
@RequestMapping("api/v1/messages")
public class MessageController extends AbstractController
{

	@Autowired
	private MessageService messageService;

	@ResponseStatusDetails
	@ApiOperation(value = "Create message", nickname = "createMessage", code = 201, httpMethod = "POST", response = Message.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Message createMessage(@RequestBody Message message)
			throws ServiceException
	{
		message.setFromUserId(getPrincipal().getId());
		return messageService.createMessage(message);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get messages by project id and user id", nickname = "getMessagesByProjectIdAndUserId", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Message> getMessagesByProjectIdAndUserId(
			@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long id) throws ServiceException
	{
		return messageService.getMessagesByProjectIdAndUserId(id, getPrincipal().getId());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get individual messages", nickname = "getIndividualMessages", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Message> getIndividualMessages() throws ServiceException
	{
		return messageService.getMessagesByUserId(getPrincipal().getId());
	}
}
