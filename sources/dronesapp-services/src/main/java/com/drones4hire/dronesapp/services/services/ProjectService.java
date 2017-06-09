package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.projects.Project.Status.NEW;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class ProjectService
{

	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private LocationService locationService;

	@Transactional(rollbackFor = Exception.class)
	public Project createProject(Project project)
	{
		locationService.createLocation(project.getLocation());
		projectMapper.createProject(project);
		createProjectPaidOption(project.getId(), project.getPaidOptions());
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createProjectPaidOption(long projectId, List<PaidOption> paidOptions)
	{
		projectMapper.createProjectPaidOption(projectId, paidOptions);
		return projectId;
	}

	@Transactional(readOnly = true)
	public Project getProjectById(long id, long principalId) throws ServiceException
	{
		checkAuthorities(id, principalId);
		return projectMapper.getProjectById(id);
	}

	@Transactional(readOnly = true)
	public List<Project> searchProjects(ProjectSearchCriteria sc, long principalId) throws ServiceException
	{
		User user = userService.getUserById(principalId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			sc.setClientId(principalId);
		} else if (user.getRoles().contains(ROLE_PILOT))
		{
			sc.setPilotId(principalId);
		}
		return projectMapper.searchProjects(sc);
	}

	@Transactional(readOnly = true)
	public List<Project> getAllProjects()
	{
		return projectMapper.getAllProjects();
	}

	@Transactional(rollbackFor = Exception.class)
	public Project updateProject(Project project)
	{
		locationService.updateLocation(project.getLocation());
		projectMapper.updateProject(project);
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProject(long id)
	{
		projectMapper.deleteProject(id);
	}

	public void checkAuthorities(long projectId, long principalId) throws ServiceException
	{
		User user = userService.getUserById(principalId);
		Project project = projectMapper.getProjectById(projectId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			if (principalId != project.getClientId())
			{
				throw new ForbiddenOperationException();
			}
		} else if (!project.getStatus().equals(NEW) && user.getRoles().contains(ROLE_PILOT))
		{
			if (principalId != project.getPilotId())
			{
				throw new ForbiddenOperationException();
			}
		}
	}
}
