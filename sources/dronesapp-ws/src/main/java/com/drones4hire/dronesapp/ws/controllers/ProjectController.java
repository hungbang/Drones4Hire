package com.drones4hire.dronesapp.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.commons.Budget;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.BidInfo;
import com.drones4hire.dronesapp.models.db.projects.Comment;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.dto.AttachmentDTO;
import com.drones4hire.dronesapp.models.dto.BidDTO;
import com.drones4hire.dronesapp.models.dto.CommentDTO;
import com.drones4hire.dronesapp.models.dto.PaidOptionDTO;
import com.drones4hire.dronesapp.models.dto.ProjectDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.AttachmentService;
import com.drones4hire.dronesapp.services.services.BidService;
import com.drones4hire.dronesapp.services.services.CommentService;
import com.drones4hire.dronesapp.services.services.PaidOptionService;
import com.drones4hire.dronesapp.services.services.ProjectService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value = "Project API")
@CrossOrigin
@RequestMapping("api/v1/projects")
public class ProjectController extends AbstractController
{

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private BidService bidService;

	@Autowired
	private PaidOptionService paidOptionService;

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Create project", nickname = "createProject", code = 201, httpMethod = "POST", response = ProjectDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProjectDTO createProject(@Valid @RequestBody ProjectDTO p) throws ServiceException
	{
		Project project = mapper.map(p, Project.class);
		project.setClientId(getPrincipal().getId());
		project.setPilotId(null);
		// TODO: 05/25/2017
		return mapper.map(projectService.createProject(project), ProjectDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get project by id", nickname = "getProjectById", code = 200, httpMethod = "GET", response = ProjectDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProjectDTO getProjectById(
			@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long id)
			throws ServiceException
	{
		Project project = projectService.getProjectById(id, getPrincipal().getId());
		ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);
		if(project.getPilotId() != null)
		{
			projectDTO.setBidId(bidService.getBidByProjectIdAndUserId(project.getId(), project.getPilotId()).getId());
		}
		return projectDTO;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update project", nickname = "updateProject", code = 200, httpMethod = "PUT", response = ProjectDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProjectDTO updateProject(@Valid @RequestBody ProjectDTO p) throws ServiceException
	{
		Project project = projectService.getProjectById(p.getId(), getPrincipal().getId());
		checkPrincipalPermissions(project.getClientId());
		project.setTitle(p.getTitle());
		project.setSummary(p.getSummary());
		project.setService(p.getService());
		project.setDuration(mapper.map(p.getDuration(), Duration.class));
		project.getLocation().setAddress(p.getLocation().getAddress());
		project.getLocation().setCoordinates(p.getLocation().getCoordinates());
		project.getLocation().setCountry(p.getLocation().getCountry());
		project.getLocation().setState(p.getLocation().getState());
		project.getLocation().setCity(p.getLocation().getCity());
		project.getLocation().setPostcode(p.getLocation().getPostcode());
		project.setBudget(mapper.map(p.getBudget(), Budget.class));
		project.setPostProductionRequired(p.getPostProductionRequired());
		project.setStartDate(p.getStartDate());
		project.setFinishDate(p.getFinishDate());
		project.setStatus(p.getStatus());
		List<PaidOption> paidOptions = new ArrayList<>();
		for(PaidOptionDTO paidOptionDTO : p.getPaidOptions())
		{
			paidOptions.add(mapper.map(paidOptionDTO, PaidOption.class));
		}
		project.setPaidOptions(paidOptions);
		return mapper.map(projectService.updateProject(project), ProjectDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete project", nickname = "deleteProject", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProject(
			@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long id)
			throws ServiceException
	{
		Project project = projectService.getProjectById(id, getPrincipal().getId());
		checkPrincipalPermissions(project.getClientId());
		projectService.deleteProject(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Search projects", nickname = "searchProjects", code = 201, httpMethod = "POST", response = SearchResult.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<ProjectSearchResult> searchProjects(@Valid @RequestBody ProjectSearchCriteria sc)
			throws ServiceException
	{
		SearchResult<ProjectDTO> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		SearchResult<ProjectSearchResult> searchResult = projectService.searchProjects(sc, getPrincipal().getId());
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		ProjectDTO projectDTO = null;
		/*for(Project project : searchResult.getResults())
		{
			projectDTO = mapper.map(project, ProjectDTO.class);
			if(project.getPilotId() != null)
			{
				projectDTO.setBidId(bidService.getBidByProjectIdAndUserId(project.getId(), project.getPilotId()).getId());
			}
			projectDTOs.add(projectDTO);
		}*/
		results.setTotalResults(searchResult.getTotalResults());
		results.setResults(projectDTOs);
		return searchResult;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Cancel project", nickname = "cancelProject", code = 200, httpMethod = "POST")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_CLIENT"})
	@RequestMapping(value = "{id}/cancel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void cancelProject(@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long id)
			throws ServiceException
	{
		projectService.cancelProject(id, getPrincipal().getId());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get comments by project id", nickname = "getCommentsByProjectId", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CommentDTO> getCommentsByProjectId(
			@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long id)
			throws ServiceException
	{
		List<Comment> comments = commentService.getCommentsByProjectId(id, getPrincipal().getId());
		List<CommentDTO> commentDTOs = new ArrayList<>();
		for (Comment comment : comments)
		{
			commentDTOs.add(mapper.map(comment, CommentDTO.class));
		}
		return commentDTOs;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Upload attachment", nickname = "uploadAttachment", code = 201, httpMethod = "POST", response = AttachmentDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
//	TODO: Need new endpoint to edit attachment for client
//	@Secured({"ROLE_PILOT"})
	@RequestMapping(value = "results", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttachmentDTO uploadAttachment(@Valid @RequestBody AttachmentDTO attachmentDTO) throws ServiceException
	{
		Attachment attach = mapper.map(attachmentDTO, Attachment.class);
		return mapper.map(attachmentService.createAttachment(attach, getPrincipal().getId()), AttachmentDTO.class);
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Remove attachment", nickname = "deleteAttachment", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "results/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAttachment(@ApiParam(value = "Id to remove attachment", required = true) @PathVariable(value = "id") long id) 
			throws ServiceException
	{
		attachmentService.deleteAttachment(id, getPrincipal().getId());
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Get bids by project id", nickname = "getBidsByProjectId", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/bids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BidDTO> getBidsByProjectId(
			@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long id)
			throws ServiceException
	{
		List<Bid> bids = bidService.getBidsByProjectId(id, getPrincipal().getId());
		List<BidDTO> bidDTOs = new ArrayList<>();
		for (Bid bid : bids)
		{
			bidDTOs.add(mapper.map(bid, BidDTO.class));
		}
		return bidDTOs;
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Create paid option (admin)", nickname = "createPaidOption", code = 201, httpMethod = "POST", response = PaidOption.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "paidoptions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption createPaidOption(@Valid @RequestBody PaidOptionDTO paidOption)
	{
		return paidOptionService.createPaidOption(mapper.map(paidOption, PaidOption.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get paid option by id", nickname = "getPaidOptionById", code = 200, httpMethod = "GET", response = PaidOption.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "paidoptions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption getPaidOptionById(
			@ApiParam(value = "Id of the paid option", required = true) @PathVariable(value = "id") long id)
	{
		return paidOptionService.getPaidOptionById(id);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all paid options", nickname = "getAllPaidOptions", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "paidoptions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PaidOption> getAllPaidOptions()
	{
		return paidOptionService.getAllPaidOptions();
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update paid option (admin)", nickname = "updatePaidOption", code = 200, httpMethod = "PUT", response = PaidOption.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "paidoptions", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaidOption updatePaidOption(@Valid @RequestBody PaidOptionDTO paidOption)
	{
		return paidOptionService.updatePaidOption(mapper.map(paidOption, PaidOption.class));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete paid option (admin)", nickname = "deletePaidOption", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "paidoptions/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePaidOption(
			@ApiParam(value = "Id of the paid option", required = true) @PathVariable(value = "id") long id)
	{
		paidOptionService.deletePaidOption(id);
	}
	

	@ResponseStatusDetails
	@ApiOperation(value = "Get bid info", nickname = "getBidInfo", code = 200, httpMethod = "GET", response = BidInfo.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}/bidInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BidInfo getBidInfo(@ApiParam(value = "Id of the project", required = true) @PathVariable(value = "id") long projectId) throws ServiceException
	{
		return bidService.getBidInfo(projectId);
	}
}
