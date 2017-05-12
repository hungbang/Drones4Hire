package com.drones4hire.dronesapp.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.auth.AuthTokenType;
import com.drones4hire.dronesapp.models.dto.auth.CredentialsType;
import com.drones4hire.dronesapp.models.dto.auth.RefreshTokenType;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.auth.JWTService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Auth API")
@CrossOrigin
@RequestMapping("api/v1")
public class AuthAPIController extends AbstractController
{
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private UserService userService;
	
	@ResponseStatusDetails
	@ApiOperation(value = "Generates auth token", nickname = "login", code = 200, httpMethod = "POST", response = AuthTokenType.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenType login(@Valid @RequestBody CredentialsType credentials) throws BadCredentialsException
	{
		AuthTokenType authToken = null;
		try
		{
			User user = userService.getUserByUsername(credentials.getUsername());
			if(user == null)
			{
				throw new UsernameNotFoundException("Invalid username: " + credentials.getUsername());
			}
			
			if(!userService.checkPassword(credentials.getPassword(), user.getPassword()))
			{
				throw new BadCredentialsException("Invalid password");
			}
			
			authToken = new AuthTokenType("Bearer", 
					jwtService.generateAuthToken(user), 
					jwtService.generateRefreshToken(user), 
					jwtService.getExpiration());
		}
		catch(Exception e)
		{
			throw new BadCredentialsException(e.getMessage());
		}
		return authToken;
	}
	
	@ResponseStatusDetails
	@ApiOperation(value = "Refreshes auth token", nickname = "refreshToken", code = 200, httpMethod = "POST", response = AuthTokenType.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="refresh", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthTokenType refresh(@RequestBody @Valid RefreshTokenType refreshToken) throws BadCredentialsException
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
			throw new BadCredentialsException(e.getMessage());
		}	
		
		return authToken;
	}
}
