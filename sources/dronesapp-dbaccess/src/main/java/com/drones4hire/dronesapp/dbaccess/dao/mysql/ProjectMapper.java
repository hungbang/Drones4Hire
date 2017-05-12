package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.Project;

public interface ProjectMapper
{
	void create(Project device);

	Project getProjectById(long id);

	List<Project> getAllProjects();

	void updateProject(Project device);

	void deleteProject(long id);
}
