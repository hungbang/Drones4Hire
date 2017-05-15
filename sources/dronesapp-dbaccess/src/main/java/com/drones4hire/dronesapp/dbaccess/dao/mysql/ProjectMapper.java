package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.models.db.projects.Project;

public interface ProjectMapper
{
	void createProject(Project project);
	
	void createProjectPaidOption(@Param("projectId") Long projectId, @Param("paidOptionId") Long paidOptionId);

	Project getProjectById(long id);

	List<Project> getAllProjects();

	void updateProject(Project project);

	void deleteProject(long id);
}
