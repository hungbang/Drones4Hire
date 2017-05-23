package com.drones4hire.dronesapp.services.services.notifications;

import static com.drones4hire.dronesapp.services.services.notifications.EmailType.CHANGE_EMAIL;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.CONFIRMATION;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.FORGOT_PASSWORD;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.amazonaws.services.simpleemail.model.Content;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.AWSException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractEmailService
{
	public final static String EMAIL_CONFIRMATION_PATH = "register/confirm";
	public final static String CHANGE_EMAIL_PATH = "email";
	public final static String CHANGE_PASSWORD_PATH = "password/change";
	
	@Autowired
	@Qualifier("freemarkerEmailConfiguration")
	private Configuration configuration;
	
	@Value("#{environmentProperties['drones4hire.ws.url']}")
	private String domain;
	
	@Value("#{environmentProperties['drones4hire.tomcat.ws.path']}")
	private String path;

	public abstract String sendEmail(EmailType type, Map<String, Object> params, String... recipients);
	
	public String sendConfirmationEmail(User user, String token) {
		try
		{
			URIBuilder builder = new URIBuilder(domain);
			builder.setPath(path + "/api/v1/auth/" + EMAIL_CONFIRMATION_PATH);
			builder.addParameter("id", user.getId().toString());
			builder.addParameter("token", token);
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(user.getClass().getSimpleName(), user);
			emailData.put("verifyUrl", builder.build().toURL().toExternalForm());
			
			return sendEmail(CONFIRMATION, emailData, user.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendChangingEmail(User user, String token) {
		try
		{
			URIBuilder builder = new URIBuilder(domain);
			builder.setPath(path + "/api/v1/auth/" + CHANGE_EMAIL_PATH);
			builder.addParameter("token", token);
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(user.getClass().getSimpleName(), user);
			emailData.put("verifyUrl", builder.build().toURL().toExternalForm());
		return sendEmail(CHANGE_EMAIL, emailData, user.getEmail());
		} catch (Exception e)
		{
			new ServiceException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendForgotPasswordEmail(User user, String token) {
		try
		{
			URIBuilder builder = new URIBuilder(domain);
			builder.setPath(path + "/api/v1/auth/" + CHANGE_PASSWORD_PATH);
			builder.addParameter("token", token);
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(user.getClass().getSimpleName(), user);
			emailData.put("verifyUrl", builder.build().toURL().toExternalForm());
			return sendEmail(FORGOT_PASSWORD, emailData, user.getEmail());
		} catch (Exception e)
		{
			new ServiceException("Can't build verification URL!", e);
		}
		return null;
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
