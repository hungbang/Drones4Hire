package com.drones4hire.dronesapp.services.services;

import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.models.db.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Transactional(readOnly = true)
	public User getUserById(long id) throws ServiceException
	{
		return userMapper.getUserById(id);
	}
	
	@Cacheable(value = "users")
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws ServiceException
	{
		return userMapper.getUserByUserName(username);
	}
	
	public boolean checkPassword(String plain, String encrypted)
	{
		return passwordEncryptor.checkPassword(plain, encrypted);
	}
}
