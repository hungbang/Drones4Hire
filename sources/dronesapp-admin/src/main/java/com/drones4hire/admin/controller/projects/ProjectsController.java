package com.drones4hire.admin.controller.projects;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteriaForAdmin;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.models.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.admin.controller.AbstractController;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ProjectService;

import javax.validation.Valid;

@Controller
@RequestMapping("projects")
public class ProjectsController extends AbstractController
{
	@Autowired
	private ProjectService projectService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openProjectsPage() throws ServiceException
	{
		return new ModelAndView("projects/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "view", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openUserViewPage() throws ServiceException
	{
		return new ModelAndView("projects/view");
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Project createProject(@RequestBody Project project)
	{
		return projectService.createProject(project);
	}
	
	@RequestMapping(value = "searchProjects", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	SearchResult<ProjectSearchResult> searchProjects(@Valid @RequestBody ProjectSearchCriteriaForAdmin sc) throws Exception
	{
		SearchResult<ProjectSearchResult> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		SearchResult<ProjectSearchResult> searchResult = projectService.searchProjectsWithAdmin(sc);
		results.setResults(searchResult.getResults());
		results.setTotalResults(searchResult.getTotalResults());
		return results;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Project getProject(@PathVariable long id) throws ServiceException
	{
		return projectService.getProjectById(id, getPrincipal().getId());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Project updateProject(@RequestBody Project project, @PathVariable long id) throws ServiceException
	{
		Project currProject = projectService.getProjectById(id, getPrincipal().getId());
//		currProject.setBudget(project.getBudget());
		currProject.setStatus(project.getStatus());
		currProject.setTitle(project.getTitle());
		currProject.setSummary(project.getSummary());
		currProject.setPostProductionRequired(project.getPostProductionRequired());
		currProject.setLocation(project.getLocation());
		return projectService.updateProject(currProject);
	}
	
}
