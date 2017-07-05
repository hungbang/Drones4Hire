package com.drones4hire.dronesapp.batchservices.tasks;

import org.springframework.beans.factory.annotation.Autowired;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.ProjectService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

public class PilotsNotificationsTask
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AWSEmailService emailService;
	
	public void runTask() throws ServiceException
	{
		
	}
}