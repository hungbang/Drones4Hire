package com.drones4hire.dronesapp.models.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RefreshTokenDTO implements Serializable
{
	private static final long serialVersionUID = -586102250911687530L;
	
	@NotNull(message = "Refresh token required")
	private String refreshToken;
	
	public RefreshTokenDTO()
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
