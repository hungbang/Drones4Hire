package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectForMapSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectOnMap;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteriaForAdmin;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectStatisticsResult;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;

public interface ProjectMapper
{
	void createProject(Project project);
	
	void createProjectPaidOption(@Param("projectId") Long projectId, @Param("paidOptions") List<PaidOption> paidOptions);

	Project getProjectById(long id);

	List<ProjectSearchResult> searchProjects(ProjectSearchCriteria sc);

	List<ProjectSearchResult> searchProjectsWithAdmin(ProjectSearchCriteriaForAdmin sc);

	Integer getProjectsSearchCount(ProjectSearchCriteria sc);

	Integer getProjectsWithAdminSearchCount(ProjectSearchCriteriaForAdmin sc);

	List<ProjectOnMap> searchProjectsForMap(ProjectForMapSearchCriteria sc);

	Integer getProjectsForMapSearchCount(ProjectForMapSearchCriteria sc);

	List<ProjectStatisticsResult> getProjectStatusesStatistic(ProjectSearchCriteria sc);

	List<Project> getLastProjects(@Param("createdAfter") Date createdAfter, @Param("status") Status status);
	
	List<Project> getAllProjects();

	void updateProject(Project project);

	void deleteProject(long id);
}
