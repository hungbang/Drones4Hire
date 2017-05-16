package com.drones4hire.dronesapp.ws.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.auth.AuthTokenType;
import com.drones4hire.dronesapp.models.dto.auth.CredentialsType;
import com.drones4hire.dronesapp.models.dto.auth.RefreshTokenType;
import com.drones4hire.dronesapp.models.dto.auth.RegistrationType;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserCredentialsException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.auth.JWTService;
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
	private Mapper mapper;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Generate auth token", nickname = "login", code = 200, httpMethod = "POST", response = AuthTokenType.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenType login(@Valid @RequestBody CredentialsType credentials) throws ServiceException
	{
		User user = userService.checkUserCredentials(credentials.getEmail(), credentials.getPassword());
		
		return new AuthTokenType("Bearer", 
				jwtService.generateAuthToken(user), 
				jwtService.generateRefreshToken(user), 
				jwtService.getExpiration());
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Refreshe auth token", nickname = "refreshToken", code = 200, httpMethod = "POST", response = AuthTokenType.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenType refresh(@RequestBody @Valid RefreshTokenType refreshToken) throws InvalidUserCredentialsException
	{
		AuthTokenType authToken = null;
		try
		{
			User jwtUser = jwtService.parseRefreshToken(refreshToken.getRefreshToken());
			User user = userService.getUserById(jwtUser.getId());
			if(user == null || !user.getPassword().equals(jwtUser.getPassword()))
			{
				throw new Exception("User password changed");
			}
			
			authToken = new AuthTokenType("Bearer", 
					jwtService.generateAuthToken(user), 
					jwtService.generateRefreshToken(user), 
					jwtService.getExpiration());
		}
		catch(Exception e)
		{
			throw new InvalidUserCredentialsException(e);
		}	
		
		return authToken;
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Register user", nickname = "register", code = 200, httpMethod = "POST", response = User.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User register(@RequestBody @Valid RegistrationType user) throws MappingException, ServiceException
	{
		return userService.registerUser(mapper.map(user, User.class), user.getRole());
	}
}
