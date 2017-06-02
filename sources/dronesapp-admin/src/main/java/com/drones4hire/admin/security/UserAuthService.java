package com.drones4hire.admin.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.UserService;

@Component
public class UserAuthService implements UserDetailsService
{
	private Logger logger = Logger.getLogger(UserAuthService.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email)
	{
		try
		{
			User user = userService.getUserByEmail(email);
			List<Role> roles = new ArrayList<Role>();
			roles.add(Role.ROLE_ADMIN);
			SecuredUser admin = new SecuredUser(user.getId(), user.getEmail(), user.getPassword(), user.getRoles(), user.isEnabled());
			return admin;
		} catch (ServiceException e)
		{
			logger.error("Can't load user from db!", e);
			throw new UsernameNotFoundException("User not found!");
		}
	}
}
