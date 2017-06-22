package com.drones4hire.dronesapp.ws.controllers;

import static com.drones4hire.dronesapp.services.services.notifications.AbstractEmailService.CHANGE_EMAIL_PATH;
import static com.drones4hire.dronesapp.services.services.notifications.AbstractEmailService.EMAIL_CONFIRMATION_PATH;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.auth.AuthTokenDTO;
import com.drones4hire.dronesapp.models.dto.auth.CredentialsDTO;
import com.drones4hire.dronesapp.models.dto.auth.RefreshTokenDTO;
import com.drones4hire.dronesapp.models.dto.auth.RegistrationDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.PayoneerService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.auth.JWTService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Auth API")
@CrossOrigin
@RequestMapping("api/v1/auth")
public class AuthController extends AbstractController
{
	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private PayoneerService payoneerService;
	
	@Autowired
	private AWSEmailService emailService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Generate auth token", nickname = "login", code = 200, httpMethod = "POST", response = AuthTokenDTO.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenDTO login(@Valid @RequestBody CredentialsDTO credentials) throws ServiceException
	{
		User user = userService.checkUserCredentials(credentials.getEmail(), credentials.getPassword());

		return new AuthTokenDTO("Bearer",
				jwtService.generateAuthToken(user),
				jwtService.generateRefreshToken(user),
				jwtService.getExpiration());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Refresh auth token", nickname = "refreshToken", code = 200, httpMethod = "POST", response = AuthTokenDTO.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenDTO refreshToken(@RequestBody @Valid RefreshTokenDTO refreshToken)
			throws ForbiddenOperationException
	{
		AuthTokenDTO authToken = null;
		try
		{
			User jwtUser = jwtService.parseRefreshToken(refreshToken.getRefreshToken());
			User user = userService.getUserById(jwtUser.getId());
			if (user == null || !user.getPassword().equals(jwtUser.getPassword()))
			{
				throw new Exception("User password changed");
			}

			authToken = new AuthTokenDTO("Bearer",
					jwtService.generateAuthToken(user),
					jwtService.generateRefreshToken(user),
					jwtService.getExpiration());
		} catch (Exception e)
		{
			throw new ForbiddenOperationException(e);
		}

		return authToken;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Register user", nickname = "register", code = 201, httpMethod = "POST", response = String.class)
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String register(@RequestBody @Valid RegistrationDTO userDTO)
			throws MappingException, ServiceException
	{
		User user = userService.registerUser(mapper.map(userDTO, User.class), userDTO.getRole());
		return payoneerService.signup(user);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Confirm email", nickname = "confirm", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = EMAIL_CONFIRMATION_PATH, method = RequestMethod.GET)
	public void confirmEmail(@RequestParam(name = "id", required = true) long id,
			@RequestParam(name = "token", required = true) String token) throws ServiceException
	{
		userService.confirmUserEmail(id, token);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Change user email", nickname = "changeUserEmail", code = 200, httpMethod = "GET")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = CHANGE_EMAIL_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void changeUserEmail(@RequestParam(name = "token", required = true) String token)
			throws ServiceException
	{
		User userByToken = jwtService.parseEmailToken(token);
		User user = userService.getUserById(userByToken.getId());
		user.setEmail(userByToken.getEmail());
		userService.updateUser(user);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Forgot password", nickname = "forgotPassword", code = 200, httpMethod = "GET", response = AuthTokenDTO.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "password/forgot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void forgotPassword(@RequestParam(name = "email", required = true) String email) throws ServiceException
	{
		User user = userService.getUserByEmail(email);
		if (user != null)
		{
			String token = jwtService.generateAuthToken(user);
			emailService.sendForgotPasswordEmail(user, token);
		}
	}
}
