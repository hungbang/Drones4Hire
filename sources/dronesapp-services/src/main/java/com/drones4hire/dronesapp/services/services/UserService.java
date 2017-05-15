package com.drones4hire.dronesapp.services.services;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserAlreadyExistException;

@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncryptor passwordEncryptor;
	
	@Transactional
	public User registerUser(User user, Role role) throws ServiceException
	{
		try
		{
			userMapper.createUser(user);
		}
		catch(DuplicateKeyException e)
		{
			throw new UserAlreadyExistException(e.getMessage());
		}
		
		// TODO: add user initialization based on role
		
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
	
	@Cacheable(value = "users")
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws ServiceException
	{
		return userMapper.getUserByUsername(username);
	}
	
	public boolean checkPassword(String plain, String encrypted)
	{
		return passwordEncryptor.checkPassword(plain, encrypted);
	}
}
