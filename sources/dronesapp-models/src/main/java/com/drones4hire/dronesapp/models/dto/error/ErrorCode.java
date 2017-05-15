package com.drones4hire.dronesapp.models.dto.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ErrorCodeSerializer.class)
public enum ErrorCode
{
	VALIDATION_ERROR(0),
	USER_ALREADY_EXIST(1001);


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
