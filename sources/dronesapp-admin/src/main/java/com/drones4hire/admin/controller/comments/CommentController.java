package com.drones4hire.admin.controller.comments;

import com.drones4hire.admin.controller.AbstractController;
import com.drones4hire.dronesapp.models.db.projects.Comment;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("comments")
public class CommentController extends AbstractController
{

	@Autowired
	private CommentService commentService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Comment createComment(@RequestBody Comment comment) throws ServiceException
	{
		return commentService.createComment(comment, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Comment updateComment(@RequestBody Comment comment) throws ServiceException
	{
		return commentService.updateComment(comment, getPrincipal().getId());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteComment(@PathVariable(value = "id") long id)
	{
		commentService.deleteComment(id);
	}
}
