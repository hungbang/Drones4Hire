package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteriaForAdmin;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.models.db.projects.Project;

public interface ProjectMapper
{
	void createProject(Project project);
	
	void createProjectPaidOption(@Param("projectId") Long projectId, @Param("paidOptions") List<PaidOption> paidOptions);

	Project getProjectById(long id);

	List<ProjectSearchResult> searchProjects(ProjectSearchCriteria sc);

	List<ProjectSearchResult> searchProjectsWithAdmin(ProjectSearchCriteriaForAdmin sc);

	Integer getProjectsSearchCount(ProjectSearchCriteria sc);

	Integer getProjectsWithAdminSearchCount(ProjectSearchCriteriaForAdmin sc);

	List<Project> getAllProjects();

	void updateProject(Project project);

	void deleteProject(long id);
}
