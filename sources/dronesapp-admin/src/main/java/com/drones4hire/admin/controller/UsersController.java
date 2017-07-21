package com.drones4hire.admin.controller;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.Feedback;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.dto.FeedbackDTO;
import com.drones4hire.dronesapp.services.services.*;
import com.drones4hire.dronesapp.services.services.util.UserRestoreService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.drones4hire.dronesapp.models.db.Message;
import com.drones4hire.dronesapp.models.db.settings.NotificationSettings;
import com.drones4hire.dronesapp.models.db.users.Company;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UsersController extends AbstractController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private NotificationSettingService settingsService;
	
	@Autowired
	private PilotLicenseService pilotLicenseService;

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserRestoreService userRestoreService;

	@Autowired
	private Mapper mapper;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "clients", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openClientsPage() throws ServiceException
	{
		return new ModelAndView("users/index");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "pilots", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openPilotsPage() throws ServiceException
	{
		return new ModelAndView("users/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "view", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openUserViewPage() throws ServiceException
	{
		return new ModelAndView("users/view");
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<User> search(@RequestBody UserSearchCriteria userSearchCriteria) throws Exception
	{
		return userService.search(userSearchCriteria);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser(@PathVariable long id) throws ServiceException
	{
		return userService.getUserById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "groups/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Group> getGroups() throws ServiceException
	{
		return groupService.getAllGroups();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/companies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Company getCompany(@PathVariable long id) throws ServiceException
	{
		return companyService.getCompanyByUserId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/notifications", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NotificationSettings getSettings(@PathVariable long id) throws ServiceException
	{
		return settingsService.getNotificationSettingsByUserId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/license", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLicense getPilotLicense(@PathVariable long id) throws ServiceException
	{
		return pilotLicenseService.getPilotLicenseByUserId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Message> getMessagesByToUserId(@PathVariable long id) throws ServiceException
	{
		return messageService.getMessagesByToUserId(id);
	}
	
	@RequestMapping(value = "{id}/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Message sendMessage(@PathVariable long id, @RequestBody Message message) throws Exception
	{
		message.setFromUserId(getPrincipal().getId());
		message.setToUserId(id);
		return messageService.createMessage(message);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUser(@RequestBody User user, @PathVariable long id) throws ServiceException
	{
		User currUser = userService.getUserById(id);
		currUser.setConfirmed(user.isConfirmed());
		currUser.setEnabled(user.isEnabled());
		currUser.setEmail(user.getEmail());
		currUser.setFirstName(user.getFirstName());
		currUser.setLastName(user.getLastName());
		currUser.setSummary(user.getSummary());
		return userService.updateUser(currUser);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/companies", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Company updateCompany(@RequestBody Company company, @PathVariable long id) throws ServiceException
	{
		Company currCompany = companyService.getCompanyByUserId(id);
		currCompany.setContactEmail(company.getContactEmail());
		currCompany.setContactName(company.getContactName());
		currCompany.setCountry(company.getCountry());
		currCompany.setState(company.getState());
		currCompany.setName(company.getName());
		currCompany.setWebURL(company.getWebURL());
		return companyService.updateCompany(currCompany);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/notifications", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NotificationSettings updateSettings(@RequestBody NotificationSettings settings, @PathVariable long id) throws ServiceException
	{
		NotificationSettings currSettings = settingsService.getNotificationSettingsByUserId(id);
		currSettings.setPlainEmail(settings.isPlainEmail());
		currSettings.setBidPlaced(settings.isBidPlaced());
		currSettings.setPaymentReceived(settings.isPaymentReceived());
		currSettings.setProjectUpdate(settings.isProjectUpdate());
		currSettings.setStaff(settings.isStaff());
		currSettings.setDronesNews(settings.isDronesNews());
		currSettings.setProjectAward(settings.isProjectAward());
		currSettings.setMarketing(settings.isMarketing());
		currSettings.setDeals(settings.isDeals());
		currSettings.setMonthlyNews(settings.isMonthlyNews());
		return settingsService.updateNotificationSettings(currSettings);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/licenses", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLicense updateLicense(@RequestBody PilotLicense license, @PathVariable long id) throws ServiceException
	{
		return pilotLicenseService.updatePilotLicense(license);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/feedbacks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Feedback> getFeedbacksByUserId(@PathVariable(value = "id") long id)
	{
		return feedbackService.getFeedbacksByUserId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "feedbacks", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Feedback updateFeedback(@Valid @RequestBody FeedbackDTO fb) throws ServiceException
	{
		Feedback feedback = feedbackService.getFeedbackById(fb.getId());
		feedback.setComment(fb.getComment());
		feedback.setMark(fb.getMark());
		return feedbackService.updateFeedback(feedback);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "feedbacks/{id}", method = RequestMethod.DELETE)
	public void deleteFeedback(@PathVariable(value = "id") long id) throws ServiceException
	{
		feedbackService.deleteFeedback(id);
	}

	@RequestMapping(value = "csv", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public void downloadCSV(@RequestBody UserSearchCriteria sc, HttpServletResponse response) throws Exception
	{
		response.setHeader("Content-Disposition", "attachment; filename=" + sc.getRole().toString().toLowerCase() + ".csv");
		userService.exportUsersToCSV(sc, response.getWriter());
		response.flushBuffer();
	}
}
