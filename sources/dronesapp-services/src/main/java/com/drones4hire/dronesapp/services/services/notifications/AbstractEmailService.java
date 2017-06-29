package com.drones4hire.dronesapp.services.services.notifications;

import static com.drones4hire.dronesapp.services.services.notifications.EmailType.ACCEPT_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.AWARD_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.CONFIRMATION;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.FORGOT_PASSWORD;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.NEW_BID_PLACE;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.NEW_BID_RECEIVE;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.REJECT_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.RETRACT_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.UPDATE_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.SUPPORT_MESSAGE;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.amazonaws.services.simpleemail.model.Content;
import com.drones4hire.dronesapp.models.db.Message;
import com.drones4hire.dronesapp.models.db.Question;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.AWSException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractEmailService
{
	public final static String LOGIN_PATH = "/#/login";
	public final static String CHANGE_PASSWORD_PATH = "password";

	@Autowired
	@Qualifier("freemarkerEmailConfiguration")
	private Configuration configuration;

	@Value("#{environmentProperties['drones4hire.url']}")
	private String baseUrl;

	@Value("#{environmentProperties['drones4hire.mail.support']}")
	private String supportEmail;

	@Autowired
	private UserService userService;
	
	public abstract String sendEmail(EmailType type, Map<String, Object> params, String... recipients);

	public String sendConfirmationEmail(User user, String token)
	{
		try
		{
			String url = baseUrl + LOGIN_PATH + "?id=" + user.getId() + "&token=" + token;

			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(user.getClass().getSimpleName(), user);
			emailData.put("verifyUrl", url);

			return sendEmail(CONFIRMATION, emailData, user.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}

	public String sendForgotPasswordEmail(User user, String token)
	{
		try
		{
//			TODO[anazarenko]: when implemented add new link
			String url = baseUrl + "/api/v1/auth/" + CHANGE_PASSWORD_PATH;
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(user.getClass().getSimpleName(), user);
			emailData.put("verifyUrl", url);
			return sendEmail(FORGOT_PASSWORD, emailData, user.getEmail());
		} catch (Exception e)
		{
			new ServiceException("Can't build verification URL!", e);
		}
		return null;
	}

	public String sendNewBidReceiveEmail(Project project, User pilot)
	{
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			User client = userService.getUserById(project.getClientId());
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(client.getClass().getSimpleName(), client);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("Pilot", pilot);
			emailData.put("projectUrl", url);

			return sendEmail(NEW_BID_RECEIVE, emailData, client.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendNewBidPlacedEmail(Project project, User pilot)
	{
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(pilot.getClass().getSimpleName(), pilot);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("projectUrl", url);

			return sendEmail(NEW_BID_PLACE, emailData, pilot.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendAwardBidEmail(Project project) throws ServiceException
	{
		User pilot = userService.getUserById(project.getPilotId());
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(pilot.getClass().getSimpleName(), pilot);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("projectUrl", url);

			return sendEmail(AWARD_BID, emailData, pilot.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendUpdateBidEmail(Project project, User pilot)
	{
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(pilot.getClass().getSimpleName(), pilot);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("projectUrl", url);

			return sendEmail(UPDATE_BID, emailData, pilot.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendRetractBidEmail(Project project, User pilot)
	{
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(pilot.getClass().getSimpleName(), pilot);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("projectUrl", url);

			return sendEmail(RETRACT_BID, emailData, pilot.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendRejectBidEmail(Project project, User pilot)
	{
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			User client = userService.getUserById(project.getClientId());
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(client.getClass().getSimpleName(), client);
			emailData.put("Pilot", pilot);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("projectUrl", url);

			return sendEmail(REJECT_BID, emailData, client.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendAcceptBidEmail(Project project, User pilot)
	{
		try
		{
			String url = baseUrl + "/#/project/" + project.getId() + "/description";
			
			User client = userService.getUserById(project.getClientId());
			Map<String, Object> emailData = new HashMap<String, Object>();
			emailData.put(client.getClass().getSimpleName(), client);
			emailData.put("Pilot", pilot);
			emailData.put(project.getClass().getSimpleName(), project);
			emailData.put("projectUrl", url);

			return sendEmail(ACCEPT_BID, emailData, client.getEmail());
		} catch (Exception e)
		{
			new AWSException("Can't build verification URL!", e);
		}
		return null;
	}
	
	public String sendQuestionEmail(Question question)
	{
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put("firstName", question.getFirstName());
		emailData.put("lastName", question.getLastName());
		emailData.put("email", question.getEmail());
		emailData.put("phone", question.getPhone());
		emailData.put("reason", question.getReason());
		emailData.put("country", question.getCountry());
		emailData.put("message", question.getMessage());
		return sendEmail(EmailType.QUESTION, emailData, supportEmail);
	}
	
	public String sendSupportMessageEmail(Message message) throws ServiceException
	{
		User user = userService.getUserById(message.getToUserId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("message", message.getMessage());
		return sendEmail(SUPPORT_MESSAGE, emailData, user.getEmail());
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
			new ServiceException("Can't load freemarker template!", e);
		}
		return new Content(body);
	}
}
