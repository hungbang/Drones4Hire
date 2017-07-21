package com.drones4hire.admin.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.*;
import com.drones4hire.dronesapp.models.db.projects.*;
import com.drones4hire.dronesapp.models.dto.FeedbackDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.services.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("projects")
public class ProjectsController extends AbstractController
{
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectManageService projectManageService;

	@Autowired
	private BidService bidService;

	@Autowired
	private UserService userService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private Mapper mapper;

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
	public @ResponseBody Project createProject(@RequestBody Project project, @RequestHeader(value = "BidAmount", required = false) BigDecimal bidAmount) throws ServiceException
	{
		if(project.getPilotId() != null && bidAmount == null)
		{
			throw new ForbiddenOperationException();
		}
		return projectManageService.createProject(project, bidAmount);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "{id}/block", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Project blockProject(@PathVariable(value = "id") Long id) throws ServiceException
	{
		Project project = projectService.getProjectById(id, getPrincipal().getId());
		project.setStatus(Project.Status.BLOCKED);
		return projectService.updateProject(project);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProject(@PathVariable(value = "id") long id)
			throws ServiceException
	{
		projectService.deleteProject(id);
	}

	@RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	SearchResult<ProjectSearchResultForAdmin> searchProjects(@RequestBody ProjectSearchCriteriaForAdmin sc)
			throws Exception
	{
		SearchResult<ProjectSearchResultForAdmin> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		SearchResult<ProjectSearchResultForAdmin> searchResult = projectManageService.searchProjectsWithAdmin(sc);
		results.setResults(searchResult.getResults());
		results.setTotalResults(searchResult.getTotalResults());
		return results;
	}

	@RequestMapping(value = "search/map", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	SearchResult<ProjectOnMap> searchProjectsForMap(@RequestBody ProjectForMapSearchCriteria sc)
			throws Exception
	{
		return projectManageService.searchProjectsForMap(sc);
	}

	@RequestMapping(value = "search/statistic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Map<Long, Map<Project.Status, ProjectStatisticsResult>> getProjectStatusesStatistic(@RequestBody ProjectSearchCriteria sc)
			throws Exception
	{
		return projectService.getProjectStatusesStatistic(sc);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Project getProject(@PathVariable long id) throws ServiceException
	{
		return projectService.getProjectById(id, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Project updateProject(@RequestBody Project project)
			throws ServiceException
	{
		Project currProject = projectService.getProjectById(project.getId(), getPrincipal().getId());
		currProject.setStatus(project.getStatus());
		currProject.setTitle(project.getTitle());
		currProject.setSummary(project.getSummary());
		currProject.setBudget(project.getBudget());
		currProject.setDuration(project.getDuration());
		currProject.setService(project.getService());
		currProject.setPostProductionRequired(project.getPostProductionRequired());
		currProject.setLocation(project.getLocation());
		currProject.setStartDate(project.getStartDate());
		currProject.setFinishDate(project.getFinishDate());
		currProject.setAttachments(project.getAttachments());
		return projectService.updateProject(currProject);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "attachments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment uploadAttachment(@RequestBody Attachment attach) throws ServiceException
	{
		return projectManageService.createAttachment(attach);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "attachments/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAttachment(@PathVariable(value = "id") long id)
			throws ServiceException
	{
		projectManageService.deleteAttachment(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/bids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Bid> getBidsByProjectId(@PathVariable(value = "id") long id)
			throws ServiceException
	{
		return bidService.getBidsByProjectId(id, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Comment> getCommentsByProjectId(@PathVariable(value = "id") long id)
			throws ServiceException
	{
		return projectManageService.getCommentsByProjectId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/feedbacks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Feedback> getFeedbacksByProjectId(@PathVariable(value = "id") long id)
	{

		return feedbackService.getFeedbacksByProjectId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "feedbacks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Feedback createFeedback(@Valid @RequestBody FeedbackDTO fb) throws ServiceException
	{
		Feedback feedback = mapper.map(fb, Feedback.class);
		feedback.setFromUser(userService.getUserById(getPrincipal().getId()));
		return projectManageService.createFeedback(feedback);
	}

	@RequestMapping(value = "csv", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	public void downloadCSV(@RequestBody ProjectSearchCriteria sc, HttpServletResponse response) throws Exception
	{
		response.setHeader("Content-Disposition", "attachment; filename=" + "projects.csv");
		projectManageService.exportProjectsToCSV(sc, response.getWriter());
		response.flushBuffer();
	}
}
