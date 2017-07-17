package com.drones4hire.dronesapp.batchservices.tasks;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static com.drones4hire.dronesapp.models.db.projects.Project.Status.NEW;

public class JobsExpirationTask
{

	private static final Logger LOGGER = LoggerFactory.getLogger(JobsExpirationTask.class);
	private static final Project.Status[] STATUSES = { NEW };

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private AWSEmailService emailService;

	ProjectSearchCriteria sc;

	public void runTask() throws ServiceException
	{
		Project.Status[] STATUSES = { Project.Status.NEW};
		ProjectSearchCriteria sc;
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.HOUR, -24);
		sc = new ProjectSearchCriteria();
		sc.setStartDateBefore(cal.getTime());
		sc.setStatuses(Arrays.asList(STATUSES));
		sc.setPage(null);
		sc.setPageSize(null);
		List<ProjectSearchResult> projects = projectMapper.searchProjects(sc);


		for (ProjectSearchResult project : projects)
		{
			project.getProject().setStatus(Project.Status.EXPIRED);
			projectMapper.updateProject(project.getProject());
			emailService.sendNewProjectExpirationEmail(project);
		}
	}
}
