package com.drones4hire.dronesapp.batchservices.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.drones4hire.dronesapp.batchservices.services.ISchedulerService;
import com.drones4hire.dronesapp.batchservices.tasks.PilotsNotificationsTask;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;


public class SchedulerService implements ISchedulerService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);

	@Autowired
	private PilotsNotificationsTask pilotsNotificationsTask;
	
	@Override
	public void executePilotNotificationTask()
	{
		try
		{
			LOGGER.info("Starting pilots notification job...");
			pilotsNotificationsTask.runTask();
			LOGGER.info("Complete pilots notification complete.");
		} catch (ServiceException e)
		{
			LOGGER.error("Can't run the job for notifying pilots. " + e);
		}
	}
}