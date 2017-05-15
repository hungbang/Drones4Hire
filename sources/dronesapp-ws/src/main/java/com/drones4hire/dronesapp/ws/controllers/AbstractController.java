package com.drones4hire.dronesapp.ws.controllers;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.dto.error.Error;
import com.drones4hire.dronesapp.models.dto.error.ErrorCode;
import com.drones4hire.dronesapp.models.dto.error.ErrorResponse;
import com.drones4hire.dronesapp.services.exceptions.UserAlreadyExistException;
import com.drones4hire.dronesapp.ws.security.SecuredUser;

public abstract class AbstractController 
{
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	protected SecuredUser getPrincipal()
	{
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user instanceof SecuredUser ? (SecuredUser) user : null;
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleUserAlreadyExistException(UserAlreadyExistException e)
	{
		ErrorResponse result = new ErrorResponse();
		result.setError(new Error(ErrorCode.USER_ALREADY_EXIST));
		return result;
	}
}
