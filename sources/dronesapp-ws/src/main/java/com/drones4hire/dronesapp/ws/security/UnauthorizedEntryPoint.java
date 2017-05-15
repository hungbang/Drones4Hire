package com.drones4hire.dronesapp.ws.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.drones4hire.dronesapp.models.dto.error.Error;
import com.drones4hire.dronesapp.models.dto.error.ErrorCode;
import com.drones4hire.dronesapp.models.dto.error.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnauthorizedEntryPoint extends LoginUrlAuthenticationEntryPoint
{
	private String error;
	
	public UnauthorizedEntryPoint(String loginUrl) throws JsonProcessingException
	{
		super(loginUrl);
		this.error = new ObjectMapper().writeValueAsString(new ErrorResponse().setError(new Error(ErrorCode.UNAUTHORIZED)));
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
	{
		response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    response.getOutputStream().println(error);
	}
}