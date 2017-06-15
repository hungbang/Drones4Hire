package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.projects.Comment;
import com.drones4hire.dronesapp.models.dto.CommentDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.CommentService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Api(value = "Comment API")
@CrossOrigin
@RequestMapping("api/v1/comments")
public class CommentController extends AbstractController
{

	@Autowired
	private CommentService commentService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Create comment", nickname = "createComment", code = 201, httpMethod = "POST", response = CommentDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CommentDTO createComment(@Valid @RequestBody CommentDTO c) throws ServiceException
	{
		Comment comment = mapper.map(c, Comment.class);
		return mapper.map(commentService.createComment(comment, getPrincipal().getId()), CommentDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update comment", nickname = "updateComment", code = 200, httpMethod = "PUT", response = CommentDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CommentDTO updateComment(@Valid @RequestBody CommentDTO c) throws ServiceException
	{
		Comment comment = commentService.getCommentById(c.getId());
		comment.setComment(c.getComment());
		return mapper.map(commentService.updateComment(comment, getPrincipal().getId()), CommentDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete comment", nickname = "deleteComment", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteComment(
			@ApiParam(value = "Id of the comment", required = true) @PathVariable(value = "id") long id)
	{
		commentService.deleteComment(id);
	}
}
