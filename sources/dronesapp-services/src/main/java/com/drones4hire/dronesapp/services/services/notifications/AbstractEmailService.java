package com.drones4hire.dronesapp.services.services.notifications;

import static com.drones4hire.dronesapp.services.services.notifications.EmailType.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.amazonaws.services.simpleemail.model.Content;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractEmailService
{
	@Autowired
	@Qualifier("freemarkerEmailConfiguration")
	private Configuration configuration;

	public abstract String sendEmail(EmailType type, Map<String, Object> params, String... recipients);
	
	public String sendConfirmationEmail(User user) {
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("verifyUrl", "Url");
		return sendEmail(CONFIRMATION, emailData, user.getEmail());
	}
	
	public String sendChangingEmail(User user) {
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("verifyUrl", "Url");
		return sendEmail(CHANGE_EMAIL, emailData, user.getEmail());
	}
	
	public String sendForgotPasswordEmail(User user) {
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("verifyUrl", "Url");
		return sendEmail(FORGOT_PASSWORD, emailData, user.getEmail());
	}
	
	protected Content buildBody(String path, Map<String, Object> params)
	{
		String body = null;
		try
		{
			Template template = configuration.getTemplate(path);
			body = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
		} catch (Exception e)
		{
			new ServiceException("Can't load freemarker template!");
		}
		return new Content(body);
	}
}
