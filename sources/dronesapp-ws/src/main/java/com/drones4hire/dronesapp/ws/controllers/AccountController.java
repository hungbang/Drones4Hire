package com.drones4hire.dronesapp.ws.controllers;

import static com.drones4hire.dronesapp.services.services.notifications.AbstractEmailService.CHANGE_PASSWORD_PATH;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.Company;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.AccountDTO;
import com.drones4hire.dronesapp.models.dto.ChangeEmailDTO;
import com.drones4hire.dronesapp.models.dto.CompanyDTO;
import com.drones4hire.dronesapp.models.dto.auth.ChangePasswordDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.CompanyService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.auth.JWTService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Account API")
@CrossOrigin
@RequestMapping("api/v1/account")
public class AccountController extends AbstractController
{
	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private AWSEmailService emailService;

	@Autowired
	private PasswordEncryptor passwordEncryptor;
	
	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get user account", nickname = "getUserAccount", code = 200, httpMethod = "GET", response = User.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getUserAccount() throws MappingException, ServiceException
	{
		return userService.getUserById(getPrincipal().getId());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user account", nickname = "updateUserAccount", code = 200, httpMethod = "PUT", response = AccountDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountDTO updateUserAccount(@RequestBody AccountDTO account)
			throws ServiceException
	{
		User user = userService.getUserById(getPrincipal().getId());
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setLocation(account.getLocation());
		user.setPhotoURL(account.getPhotoURL());
		user.setIntroduction(account.getIntroduction());
		user.setSummary(account.getSummary());
		return mapper.map(userService.updateUser(user), AccountDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get user company", nickname = "getUserCompany", code = 200, httpMethod = "GET", response = CompanyDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "company", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CompanyDTO getUserCompany()
	{
		return mapper.map(companyService.getCompanyByUserId(getPrincipal().getId()), CompanyDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user company", nickname = "updateUserCompany", code = 200, httpMethod = "PUT", response = CompanyDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "company", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CompanyDTO updateUserCompany(@RequestBody CompanyDTO c)
			throws ServiceException
	{
		Company company = companyService.getCompanyById(c.getId());
		if (company.getUserId() == getPrincipal().getId())
		{
			company.setName(c.getName());
			company.setWebURL(c.getWebURL());
			company.setContactName(c.getContactName());
			company.setContactEmail(c.getContactEmail());
			company.setCountry(c.getCountry());
		} else
		{
			throw new ForbiddenOperationException("Invalid user");
		}
		return mapper.map(companyService.updateCompany(company), CompanyDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user email", nickname = "updateUserEmail", code = 200, httpMethod = "POST")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserEmail(@RequestBody ChangeEmailDTO email)
			throws ServiceException
	{
		User user = userService.getUserById(getPrincipal().getId());
		user = userService.checkUserCredentials(user.getEmail(), email.getPassword());
		user.setEmail(email.getEmail());
		emailService.sendChangingEmail(user, jwtService.generateEmailToken(user.getId(), email.getEmail()));
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Change password", nickname = "changePassword", code = 200, httpMethod = "POST")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = CHANGE_PASSWORD_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void changePassword(@Valid @RequestBody ChangePasswordDTO changePassword) throws ServiceException
	{
		User user = userService.getUserById(getPrincipal().getId());
		user.setPassword(passwordEncryptor.encryptPassword(changePassword.getPassword()));
		userService.updateUser(user);
	}
}
