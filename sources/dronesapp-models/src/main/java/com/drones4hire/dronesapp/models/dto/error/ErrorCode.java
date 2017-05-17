package com.drones4hire.dronesapp.models.dto.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ErrorCodeSerializer.class)
public enum ErrorCode
{
	VALIDATION_ERROR(0),
	INVALID_VALUE(1001),
	
	UNAUTHORIZED(401),
	FORBIDDEN_OPERATION(403),
	
	USER_ALREADY_EXIST(1001),
	USER_NOT_CONFIRMED(1002),
	INVALID_USER_STATUS(1003);

	private int code;

	private ErrorCode(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}
}
