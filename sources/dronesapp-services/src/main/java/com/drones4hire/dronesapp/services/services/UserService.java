package com.drones4hire.dronesapp.services.services;

import java.util.Arrays;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserCredentialsException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserAlreadyExistException;

@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private PilotLicenseService pilotLicenseService;
	
	@Autowired
	private NotificationSettingService notificationSettingService;

	@Autowired
	private PasswordEncryptor passwordEncryptor;
	
	@Transactional(rollbackFor=Exception.class)
	public User registerUser(User user, Role role) throws ServiceException
	{
		if(!Arrays.asList(Role.ROLE_CLIENT, Role.ROLE_PILOT).contains(role))
		{
			throw new ForbiddenOperationException("Invalid role for registration");
		}
		
		try
		{
			user.setEnabled(true);
			user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
			userMapper.createUser(user);
			
			// Add group with default user role
			Group group = groupService.getGroupByRole(role);
			userMapper.createUserGroup(user, group);
			
			// Initialize default notification settings
			notificationSettingService.createDefaultNotificationSettings(user);
		}
		catch(DuplicateKeyException e)
		{
			throw new UserAlreadyExistException("Duplicate user credentials");
		}
		
		if(Role.ROLE_PILOT.equals(role))
		{
			// Initialize default profile
			profileService.createDefaultProfile(user);
			
			// Initialize default license
			pilotLicenseService.createDefaultPilotLicense(user);
		}
		
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserById(long id) throws ServiceException
	{
		return userMapper.getUserById(id);
	}
	
	@Transactional
	public User createUser(User user) throws ServiceException
	{
		userMapper.createUser(user);
		return user;
	}
	
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws ServiceException
	{
		return userMapper.getUserByUsername(username);
	}
	
	@Transactional(readOnly = true)
	public User getUserByEmail(String email) throws ServiceException
	{
		return userMapper.getUserByEmail(email);
	}
	
	public User checkUserCredentials(String email, String password) throws ServiceException
	{
		User user = getUserByEmail(email);
		if(user == null || !passwordEncryptor.checkPassword(password, user.getPassword()))
		{
			throw new InvalidUserCredentialsException("Invalid credentials");
		}
		return user;
	}
}
