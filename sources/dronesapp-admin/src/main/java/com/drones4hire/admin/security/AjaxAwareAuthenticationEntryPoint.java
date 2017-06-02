package com.drones4hire.admin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint
{
	public AjaxAwareAuthenticationEntryPoint(String loginUrl)
	{
		super(loginUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException
	{
		if (isAjax(request))
		{
			response.sendError(HttpStatus.UNAUTHORIZED.value());
		} else
		{
			super.commence(request, response, authException);
		}
	}

	public static boolean isAjax(HttpServletRequest request)
	{
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}
}