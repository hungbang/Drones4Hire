package com.drones4hire.admin.controller.users;

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

import com.drones4hire.admin.controller.AbstractController;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.UserService;

@Controller
@RequestMapping("users")
public class UsersController extends AbstractController
{
	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openUsersPage() throws ServiceException
	{
		return new ModelAndView("users/index");
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "view", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView openUserViewPage() throws ServiceException
	{
		return new ModelAndView("users/view");
	}
	
	@RequestMapping(value = "searchUsers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody SearchResult<User> searchUsers(@RequestBody UserSearchCriteria userSearchCriteria) throws Exception
	{
		return userService.searchUsers(userSearchCriteria);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUser(@PathVariable long id) throws ServiceException
	{
		return userService.getUserById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User updateUser(@RequestBody User user, @PathVariable long id) throws ServiceException
	{
		User currUser = userService.getUserById(id);
		currUser.setConfirmed(user.isConfirmed());
		currUser.setEnabled(user.isEnabled());
		currUser.setEmail(user.getEmail());
		currUser.setFirstName(user.getFirstName());
		currUser.setLastName(user.getLastName());
		currUser.setSummary(user.getSummary());
		return userService.updateUser(currUser);
	}
}
