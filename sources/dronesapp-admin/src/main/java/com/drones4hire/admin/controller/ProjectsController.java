package com.drones4hire.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.*;
import com.drones4hire.dronesapp.models.db.projects.*;
import com.drones4hire.dronesapp.models.dto.FeedbackDTO;
import com.drones4hire.dronesapp.services.services.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Controller
@RequestMapping("projects")
public class ProjectsController extends AbstractController
{
	@Autowired
	private ProjectService projectService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private BidService bidService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FeedbackService feedbackService;

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

//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody Project createProject(@RequestBody Project p, @RequestHeader(value = "BidAmount", required = false) BigDecimal bidAmount) throws ServiceException
//	{
//		return projectService.createProject(p, getPrincipal().getId(), bidAmount);
//	}

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
	SearchResult<ProjectSearchResult> searchProjects(@RequestBody ProjectSearchCriteriaForAdmin sc)
			throws Exception
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

	@RequestMapping(value = "search/map", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	SearchResult<ProjectOnMap> searchProjectsForMap(@RequestBody ProjectForMapSearchCriteria sc)
			throws Exception
	{
		return projectService.searchProjectsForMap(sc, getPrincipal().getId());
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
	@RequestMapping(value = "results", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment uploadAttachment(@RequestBody Attachment attach) throws ServiceException
	{
		return attachmentService.createAttachment(attach, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "results/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAttachment(@PathVariable(value = "id") long id)
			throws ServiceException
	{
		attachmentService.deleteAttachment(id, getPrincipal().getId());
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
		return commentService.getCommentsByProjectId(id, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/feedbacks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Feedback> getFeedbacksByProjectId(@PathVariable(value = "id") long id)
	{

		return feedbackService.getFeedbacksByProjectId(id);
	}
}
