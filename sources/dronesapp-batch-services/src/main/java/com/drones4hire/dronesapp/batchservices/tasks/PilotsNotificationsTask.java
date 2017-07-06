package com.drones4hire.dronesapp.batchservices.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchCriteria;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ProjectService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

public class PilotsNotificationsTask
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PilotsNotificationsTask.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AWSEmailService emailService;
	
	public void runTask() throws ServiceException
	{
		Map<Long, List<Project>> results = new HashMap<Long, List<Project>>();
		Map<Long, User> users = new HashMap<Long, User>();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.HOUR, -24);
		
		LOGGER.info("Date from is " + cal.getTime());
		List<Project> projects = projectService.getLastProjects(cal.getTime(), Status.NEW);
		LOGGER.info("Found project numbers: " + projects.size());
		
		for (Project project : projects)
		{
			List<User> pilots = userService.getUsersNearLocation(project.getLocation(), Role.ROLE_PILOT, SearchCriteria.MILE);
			LOGGER.info("Found pilots: " + pilots.size() + " for project " + project.getTitle());
			for (User pilot : pilots)
			{
				List<Project> currProjects = results.get(pilot.getId());
				if(currProjects == null) {
					currProjects = new ArrayList<Project>();
				}
				currProjects.add(project);
				results.put(pilot.getId(), currProjects);
				users.put(pilot.getId(), pilot);
			}
		}
		for (Entry<Long, List<Project>> entry : results.entrySet())
		{
			emailService.sendNewProjectPostedEmail(entry.getValue(), users.get(entry.getKey()));
		}
	}
}