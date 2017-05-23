package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.projects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService
{

	@Autowired
	private ProjectMapper projectMapper;

	@Transactional(rollbackFor = Exception.class)
	public Project createProject(Project project)
	{
		projectMapper.createProject(project);
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createProjectPaidOption(long projectId, long paidOptionId)
	{
		projectMapper.createProjectPaidOption(projectId, paidOptionId);
		return projectId;
	}

	@Transactional(readOnly = true)
	public Project getProjectById(long id)
	{
		return projectMapper.getProjectById(id);
	}

	@Transactional(readOnly = true)
	public List<Project> getAllProjects()
	{
		return projectMapper.getAllProjects();
	}

	@Transactional(rollbackFor = Exception.class)
	public Project updateProject(Project project)
	{
		projectMapper.updateProject(project);
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProject(long id)
	{
		projectMapper.deleteProject(id);
	}
}
