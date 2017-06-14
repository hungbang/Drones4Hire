package com.drones4hire.dronesapp.models.dto.auth;

import java.io.Serializable;

import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthTokenDTO implements Serializable
{
	private static final long serialVersionUID = -586102250911687530L;
	
	private String type;
	
	private String accessToken;
	
	private String refreshToken;
	
	private int expiresIn;

	private User user;

	private Wallet wallet;
	
	public AuthTokenDTO()
	{
	}
	
	public AuthTokenDTO(String type, String accessToken, String refreshToken, int expiresIn, User user, Wallet wallet)
	{
		this.type = type;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
		this.user = user;
		this.wallet = wallet;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public String getRefreshToken()
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}

	public int getExpiresIn()
	{
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn)
	{
		this.expiresIn = expiresIn;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Wallet getWallet()
	{
		return wallet;
	}

	public void setWallet(Wallet wallet)
	{
		this.wallet = wallet;
	}
}
