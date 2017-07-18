package com.drones4hire.dronesapp.batchservices.tasks;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.util.UserRestoreService;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersRestoreTask
{

	private static final String FILE_PATH = "C://Users//Bogdan//Downloads//users.json";

	@Autowired
	private UserRestoreService userRestoreService;

	public void runTask() throws ServiceException
	{
		userRestoreService.restoreUsers(FILE_PATH);
	}
}
