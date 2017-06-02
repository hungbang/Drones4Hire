package com.drones4hire.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.admin.dto.forms.SigninForm;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;


@Controller
public class SigninController
{
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "signin", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openSigninPage() throws ServiceException
	{
		return new ModelAndView("signin", "signinForm", new SigninForm("url", false));
	}

	@RequestMapping(value = "/signin/failed", method = RequestMethod.GET)
	public ModelAndView signinFailed(ModelMap model) throws ServiceException
	{
		return new ModelAndView("signin", "signinForm", new SigninForm("url", true));
	}
}
