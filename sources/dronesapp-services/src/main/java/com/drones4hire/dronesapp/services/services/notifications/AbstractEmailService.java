package com.drones4hire.dronesapp.services.services.notifications;

import static com.drones4hire.dronesapp.services.services.notifications.EmailType.ACCEPT_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.AWARD_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.CLIENT_EMAIL_CONFIRMED;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.CONFIRMATION;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.FORGOT_PASSWORD;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.NEW_BID_RECEIVE;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.NEW_COMMENT_RECEIVE;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.PILOT_APPROVED;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.PROJECT_POSTED;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.REJECT_BID;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.RELEASE_PAYMENT;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.SUBMIT_PAYMENT;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.SUPPORT_MESSAGE;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.UPLOAD_PROJECT_RESULT;
import static com.drones4hire.dronesapp.services.services.notifications.EmailType.UP_EMAIL_CONFIRMATION;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.amazonaws.services.simpleemail.model.Content;
import com.drones4hire.dronesapp.models.db.Message;
import com.drones4hire.dronesapp.models.db.Question;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractEmailService
{
	public final static String CONFIRM_REGISTRATION_PATH = "/#/login";
	public final static String RESET_PASSWORD_PATH = "/#/password/reset";
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
		String url = baseUrl + CONFIRM_REGISTRATION_PATH + "?token=" + token;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("verifyUrl", url);
		return sendEmail(CONFIRMATION, emailData, user.getEmail());
	}
	
	public String sendUpConfirmationEmail(User user, String token)
	{
		String url = baseUrl + CONFIRM_REGISTRATION_PATH + "?token=" + token;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("verifyUrl", url);
		return sendEmail(UP_EMAIL_CONFIRMATION, emailData, user.getEmail());
	}
	
	public String sendClientEmailConfirmedEmail(User user)
	{
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		return sendEmail(CLIENT_EMAIL_CONFIRMED, emailData, user.getEmail());
	}
	
	public String sendPilotApprovedEmail(User user)
	{
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		return sendEmail(PILOT_APPROVED, emailData, user.getEmail());
	}

	public String sendForgotPasswordEmail(User user, String token)
	{
		String url = baseUrl + RESET_PASSWORD_PATH + "?token=" + token;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("verifyUrl", url);
		return sendEmail(FORGOT_PASSWORD, emailData, user.getEmail());
	}
	
	public String sendNewBidReceiveEmail(Project project) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		User client = userService.getUserById(project.getClientId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(NEW_BID_RECEIVE, emailData, client.getEmail());
	}

	public void sendNewProjectPostedEmail(Project project) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		List<User> pilots = null;
		for (User pilot : pilots)
		{
			emailData.put(pilot.getClass().getSimpleName(), pilot);
			sendEmail(PROJECT_POSTED, emailData, pilot.getEmail());
		}
	}
	
	public String sendNewCommentReceiveEmail(Project project) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		User client = userService.getUserById(project.getClientId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(NEW_COMMENT_RECEIVE, emailData, client.getEmail());
	}
	
	public String sendReleasePaymentEmail(Project project, User pilot)
	{
		String url = baseUrl + "/#/withdrawal-request";
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(pilot.getClass().getSimpleName(), pilot);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("withdrawUrl", url);
		return sendEmail(RELEASE_PAYMENT, emailData, pilot.getEmail());
	}

	public String sendSubmitPaymentEmail(Project project, User client, Transaction transaction) throws ServiceException
	{
		User pilot = userService.getNotNullUser(project.getPilotId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put("Pilot", pilot);
		emailData.put(transaction.getClass().getSimpleName(), transaction);
		emailData.put(project.getClass().getSimpleName(), project);
		return sendEmail(SUBMIT_PAYMENT, emailData, client.getEmail());
	}
	
	public String sendAwardBidEmail(Project project) throws ServiceException
	{
		User pilot = userService.getUserById(project.getPilotId());
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(pilot.getClass().getSimpleName(), pilot);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(AWARD_BID, emailData, pilot.getEmail());
	}
	
	public String sendRejectBidEmail(Project project, User pilot, Bid bid) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		User client = userService.getUserById(project.getClientId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put("Pilot", pilot);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		emailData.put(bid.getClass().getSimpleName(), bid);
		return sendEmail(REJECT_BID, emailData, client.getEmail());
	}
	
	public String sendAcceptBidEmail(Project project, User pilot) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		User client = userService.getUserById(project.getClientId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put("Pilot", pilot);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(ACCEPT_BID, emailData, client.getEmail());
	}
	
	public String sendUploadProjectResultEmail(Project project) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		User client = userService.getUserById(project.getClientId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(UPLOAD_PROJECT_RESULT, emailData, client.getEmail());
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
