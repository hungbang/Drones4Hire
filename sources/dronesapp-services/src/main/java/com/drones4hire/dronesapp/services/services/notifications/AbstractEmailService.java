package com.drones4hire.dronesapp.services.services.notifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
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
import com.drones4hire.dronesapp.services.services.NotificationSettingService;
import com.drones4hire.dronesapp.services.services.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;

import static com.drones4hire.dronesapp.services.services.notifications.EmailType.*;

public abstract class AbstractEmailService
{
	public final static String CONFIRM_REGISTRATION_PATH = "/#/login";
	public static final String SIGN_UP_PATH = "/#/sign-up";
	public final static String RESET_PASSWORD_PATH = "/#/password/reset";
	public final static String CHANGE_PASSWORD_PATH = "password";
	public final static String EDIT_PROJECT_PATH = "/#/project/manage/edit/";
	public final static String START_PROJECT_PATH = "/#/project/manage/add";
	public final static String PILOT_PROFILE_PATH = "/#/account/pilot/details";
	public final static String MY_PROJECTS_PATH = "/#/my-projects";
	public final static String PROJECT_PREFIX_PATH = "/#/project";

	@Autowired
	@Qualifier("freemarkerEmailConfiguration")
	private Configuration configuration;

	@Value("#{environmentProperties['drones4hire.url']}")
	private String baseUrl;

	@Value("#{environmentProperties['drones4hire.mail.support']}")
	private String supportEmail;

	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationSettingService settingService;
	
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
		String url = baseUrl + CONFIRM_REGISTRATION_PATH;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("url", url);
		return sendEmail(CLIENT_EMAIL_CONFIRMED, emailData, user.getEmail());
	}
	
	public String sendPilotApprovedEmail(User user)
	{
		String url = baseUrl + PILOT_PROFILE_PATH;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("url", url);
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
		User client = userService.getNotNullUser(project.getClientId());
		String url = baseUrl +  PROJECT_PREFIX_PATH + "/" + project.getId() + "/description";
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(NEW_BID_RECEIVE, emailData, client.getEmail());
	}

	public String sendNewProjectPostedEmail(List<Project> projects, User pilot) throws ServiceException
	{
		String url = baseUrl + "/#/project/${project.id}/description";
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put("baseUrl", baseUrl + PROJECT_PREFIX_PATH);
		emailData.put("descriptionSuffix", "/description");
		emailData.put("Projects", projects);
		emailData.put(pilot.getClass().getSimpleName(), pilot);
		return sendEmail(PROJECT_POSTED, emailData, pilot.getEmail());
	}

	public String sendNewProjectExpirationEmail(ProjectSearchResult project, User user) throws ServiceException
	{
		String url = baseUrl + EDIT_PROJECT_PATH +  project.getId();
		String projectsUrl = baseUrl + MY_PROJECTS_PATH;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put("url", url);
		emailData.put("baseUrl", projectsUrl);
		emailData.put("project", project);
		emailData.put("user", user);
		return sendEmail(JOB_EXPIRATION, emailData, user.getEmail());
	}
	
	public String sendNewCommentReceiveEmail(Project project) throws ServiceException
	{
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		User client = userService.getNotNullUser(project.getClientId());
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
		String url = baseUrl + "/#/dashboard/client/1";
		String startUrl = baseUrl + START_PROJECT_PATH;
		User pilot = userService.getNotNullUser(project.getPilotId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(client.getClass().getSimpleName(), client);
		emailData.put("Pilot", pilot);
		emailData.put(transaction.getClass().getSimpleName(), transaction);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("dashboardUrl", url);
		emailData.put("startUrl", startUrl);
		return sendEmail(SUBMIT_PAYMENT, emailData, client.getEmail());
	}
	
	public String sendAwardBidEmail(Project project) throws ServiceException
	{
		User pilot = userService.getNotNullUser(project.getPilotId());
		User client = userService.getNotNullUser(project.getClientId());
		String url = baseUrl + "/#/project/" + project.getId() + "/description";
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(pilot.getClass().getSimpleName(), pilot);
		emailData.put("client", client);
		emailData.put(project.getClass().getSimpleName(), project);
		emailData.put("projectUrl", url);
		return sendEmail(AWARD_BID, emailData, pilot.getEmail());
	}
	
	public String sendRejectBidEmail(Project project, User pilot, Bid bid) throws ServiceException
	{
		String url = baseUrl + PROJECT_PREFIX_PATH + "/" + project.getId() + "/description";
		User client = userService.getNotNullUser(project.getClientId());
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
		String url = baseUrl + PROJECT_PREFIX_PATH + "/" + project.getId() + "/description";
		User client = userService.getNotNullUser(project.getClientId());
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
		User client = userService.getNotNullUser(project.getClientId());
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
		User user = userService.getNotNullUser(message.getToUserId());
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put(user.getClass().getSimpleName(), user);
		emailData.put("message", message.getMessage());
		return sendEmail(SUPPORT_MESSAGE, emailData, user.getEmail());
	}

	public String sendRestoreUserEmail(com.drones4hire.dronesapp.services.services.util.model.restore.User user, String token) throws ServiceException
	{
		String url = baseUrl + SIGN_UP_PATH + "?token=" + token;
		Map<String, Object> emailData = new HashMap<String, Object>();
		emailData.put("user", user);
		emailData.put("url", url);
		return sendEmail(USER_RESTORE, emailData, user.getEmail());
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
