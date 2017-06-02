package com.drones4hire.admin.servlet.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.drones4hire.admin.security.SecuredUser;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	private Logger logger = Logger.getLogger(LoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest rq, HttpServletResponse rs, Authentication auth) throws IOException, ServletException
	{
        SecuredUser user = (SecuredUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
        super.onAuthenticationSuccess(rq, rs, auth);
	}
}
