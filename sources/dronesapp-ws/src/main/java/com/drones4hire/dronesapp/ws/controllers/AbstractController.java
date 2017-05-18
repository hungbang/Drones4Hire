package com.drones4hire.dronesapp.ws.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.dto.error.AdditionalErrorData;
import com.drones4hire.dronesapp.models.dto.error.Error;
import com.drones4hire.dronesapp.models.dto.error.ErrorCode;
import com.drones4hire.dronesapp.models.dto.error.ErrorResponse;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserCredentialsException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserStatusException;
import com.drones4hire.dronesapp.services.exceptions.UserAlreadyExistException;
import com.drones4hire.dronesapp.services.exceptions.UserNotConfirmedException;
import com.drones4hire.dronesapp.ws.security.SecuredUser;

public abstract class AbstractController
{
	private static final GrantedAuthority ADMIN = new SimpleGrantedAuthority(Role.ROLE_ADMIN.name());
	
	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	protected SecuredUser getPrincipal()
	{
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user instanceof SecuredUser ? (SecuredUser) user : null;
	}
	
	/**
	 * If user not ADMIN limit CRUD operations for objects where he is owner.
	 * @param objectUserId
	 * @throws ForbiddenOperationException
	 */
	protected void checkPrincipalPermissions(Long userId) throws ForbiddenOperationException
	{
		if(!getPrincipal().getAuthorities().contains(ADMIN) && getPrincipal().getId() != userId)
		{
			throw new ForbiddenOperationException();
		}
	}

	@ExceptionHandler(InvalidUserCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorResponse handleInvalidUserCredentialsException(InvalidUserCredentialsException e)
	{
		ErrorResponse result = new ErrorResponse();
		result.setError(new Error(ErrorCode.UNAUTHORIZED));
		return result;
	}

	@ExceptionHandler(UserNotConfirmedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleUserNotConfirmedException(UserNotConfirmedException e)
	{
		ErrorResponse result = new ErrorResponse();
		result.setError(new Error(ErrorCode.USER_NOT_CONFIRMED));
		return result;
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

	@ExceptionHandler(ForbiddenOperationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleForbiddenOperationException(ForbiddenOperationException e)
	{
		ErrorResponse result = new ErrorResponse();
		result.setError(new Error(ErrorCode.FORBIDDEN_OPERATION));
		return result;
	}

	@ExceptionHandler(InvalidUserStatusException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorResponse handleInvalidUserStatusException(InvalidUserStatusException e)
	{
		ErrorResponse result = new ErrorResponse();
		result.setError(new Error(ErrorCode.INVALID_USER_STATUS));
		return result;
	}

	@ExceptionHandler(
	{ MethodArgumentNotValidException.class, BindException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleBindException(Exception e)
	{
		ErrorResponse result = new ErrorResponse();
		BindingResult bindingResult = null;

		result.setError(new Error(ErrorCode.VALIDATION_ERROR));

		if (e instanceof MethodArgumentNotValidException)
		{
			bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
		} else if (e instanceof BindException)
		{
			bindingResult = ((BindException) e).getBindingResult();
		}

		if (null != bindingResult)
		{
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			for (FieldError fieldError : fieldErrors)
			{
				Error error = new Error(ErrorCode.INVALID_VALUE, fieldError.getDefaultMessage());

				Object rejectedValue = fieldError.getRejectedValue();

				if ((rejectedValue instanceof String) || (rejectedValue instanceof Number))
				{
					AdditionalErrorData additionalErrorData = new AdditionalErrorData();

					additionalErrorData.setValue(rejectedValue);
					error.setAdditional(additionalErrorData);
				}

				result.getValidationErrors().add(error);
			}
		}

		return result;
	}
}