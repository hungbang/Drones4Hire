package com.drones4hire.admin.controller;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.admin.security.SecuredUser;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;

public abstract class AbstractController
{
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
	protected SecuredUser getPrincipal()
	{
		SecuredUser securedUser = null;
		try
		{
			securedUser = (SecuredUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e)
		{
//			throw new AuthenticationException();
		}
		return securedUser;
	}
	
//	protected long getPrincipalId() throws AuthenticationException
//	{
//		return getPrincipal().getId();
//	}
//	
//	protected UserRole getPrincipalRole() throws AuthenticationException
//	{
//		return getPrincipal().getRole();
//	}
//	
//	public boolean isAdmin() throws AuthenticationException
//	{
//		return getPrincipalRole().equals(Role.ROLE_ADMIN);
//	}

	// Error handling
	
	@ExceptionHandler(ForbiddenOperationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public String handleForbiddenOperationException(ForbiddenOperationException e)
	{
		return e.getMessage();
	}
}
