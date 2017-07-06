package com.drones4hire.dronesapp.batchservices.tasks;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ProjectService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

public class PilotsNotificationsTask
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AWSEmailService emailService;
	
	public void runTask() throws ServiceException
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -1);
		
		ProjectSearchCriteria sc = new ProjectSearchCriteria();
		sc.setCreatedAtAfter(cal.getTime());
		sc.setStatuses(Arrays.asList(Status.NEW));
		sc.setPageSize(projectService.getProjectsSearchCount(sc));
		
		List<ProjectSearchResult> results = projectService.searchProjectsForBatch(sc);
		for (ProjectSearchResult projectSearchResult : results)
		{
			Project project = projectSearchResult.getProject();
		}
	}
}