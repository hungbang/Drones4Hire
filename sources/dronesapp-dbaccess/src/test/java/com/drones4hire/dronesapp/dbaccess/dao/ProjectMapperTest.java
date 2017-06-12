package com.drones4hire.dronesapp.dbaccess.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.db.services.Service;

@Test
@ContextConfiguration("classpath:com/drones4hire/dronesapp/dbaccess/dbaccess-test.xml")
public class ProjectMapperTest extends AbstractTestNGSpringContextTests
{
	/**
     * Turn this on to enable this test
     */
    private static final boolean ENABLED = false;

	@Autowired
	private ProjectMapper projectMapper;

	private static final Project PROJECT = new Project()
	{

		private static final long serialVersionUID = 1L;
		{
			setTitle("T1");
			setSummary("S1");
			setClientId(1L);
			setService(new Service(1L));
			setDuration(new Duration(1L));
			setLocation(new Location(1L));
			setBudget(new Budget(1L));
			setPostProductionRequired(true);
			setStatus(Status.NEW);
			setStartDate(new Date());
			setStartDate(new Date());
		}
	};

	@Test(enabled = ENABLED)
	public void testCreateProject()
	{
		projectMapper.createProject(PROJECT);
		assertNotEquals(PROJECT.getId(), 0, "Project id not autogenerated");
	}
	
	@Test(enabled = ENABLED, dependsOnMethods = { "testCreateProject" })
	public void createProjectPaidOption()
	{
		List<PaidOption> paidOptions = new ArrayList<>();
		PaidOption paidOption = new PaidOption();
		paidOption.setId(1L);
		paidOptions.add(paidOption);
		projectMapper.createProjectPaidOption(PROJECT.getId(), paidOptions);
		assertEquals(projectMapper.getProjectById(PROJECT.getId()).getPaidOptions().size() , 1, "Project paid option not created");
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "createProjectPaidOption" })
	public void testGetProjectById()
	{
		check(projectMapper.getProjectById(PROJECT.getId()));
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "testGetProjectById" })
	public void getAllProjects()
	{
		List<Project> projects = projectMapper.getAllProjects();
		check(projects.get(0));
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "getAllProjects" })
	public void testUpdateProject()
	{
		PROJECT.setTitle("T2");
		PROJECT.setSummary("S2");
		PROJECT.setClientId(1L);
		PROJECT.setPilotId(1L);
		PROJECT.setPostProductionRequired(false);
		PROJECT.setStatus(Status.IN_PROGRESS);
		PROJECT.setStartDate(new Date());
		PROJECT.setStartDate(new Date());
		
		projectMapper.updateProject(PROJECT);
		check(projectMapper.getProjectById(PROJECT.getId()));
	}

	@Test(enabled = ENABLED, dependsOnMethods = { "testUpdateProject" })
	public void testDeleteProject()
	{
		projectMapper.deleteProject(PROJECT.getId());
		assertNull(projectMapper.getProjectById(PROJECT.getId()));
	}

	private void check(Project project)
	{
		assertEquals(project.getId(), PROJECT.getId(), "Project id must match");
		assertEquals(project.getTitle(), PROJECT.getTitle(), "Project title must match");
		assertEquals(project.getSummary(), PROJECT.getSummary(), "Project summary must match");
		assertEquals(project.getClientId(), PROJECT.getClientId(), "Project client ID must match");
		assertEquals(project.getPilotId(), PROJECT.getPilotId(), "Project pilot ID must match");
		assertEquals(project.getPostProductionRequired(), PROJECT.getPostProductionRequired(), "Project post production required must match");
		assertEquals(project.getStatus(), PROJECT.getStatus(), "Project status must match");
//		assertEquals(project.getStartDate(), PROJECT.getStartDate(), "Project start date must match");
//		assertEquals(project.getFinishDate(), PROJECT.getFinishDate(), "Project finish date must match");
		assertEquals(project.getBudget().getId(), PROJECT.getBudget().getId(), "Project budget must match");
		assertEquals(project.getLocation().getId(), PROJECT.getLocation().getId(), "Project location must match");
		assertEquals(project.getDuration().getId(), PROJECT.getDuration().getId(), "Project duration must match");
		assertEquals(project.getService().getId(), PROJECT.getService().getId(), "Project service must match");
	}
}
