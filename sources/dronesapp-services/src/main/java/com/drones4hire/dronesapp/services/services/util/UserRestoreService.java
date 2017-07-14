package com.drones4hire.dronesapp.services.services.util;

import com.drones4hire.dronesapp.services.services.auth.JWTService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;
import com.drones4hire.dronesapp.services.services.util.model.restore.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRestoreService
{

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AWSEmailService emailService;

	private static final String URL = "https://stag.drones4hire.com/#/sign-up";
	private static final String FILE_PATH = "C://Users//Bogdan//Downloads//users.json";

	private ObjectMapper mapper;

	@Transactional
	public void restoreUsers()
	{
		List<User> users = getUsers();
		try
		{
			for (User user : users)
			{
				String token = jwtService.generateUserRestoreToken(user);
				emailService.sendRestoreUserEmail(user, URL, token);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Transactional
	public List<User> getUsers()
	{
		List<User> users = null;
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try
		{
			users = mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {});
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return users;
	}
}
