package com.drones4hire.dronesapp.models.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RefreshTokenType implements Serializable
{
	private static final long serialVersionUID = -586102250911687530L;
	
	@NotNull
	private String refreshToken;
	
	public RefreshTokenType()
	{
	}
	
	public String getRefreshToken()
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}
}
