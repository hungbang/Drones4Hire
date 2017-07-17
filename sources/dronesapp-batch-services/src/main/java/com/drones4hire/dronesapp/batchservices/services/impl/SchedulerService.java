package com.drones4hire.dronesapp.batchservices.services.impl;

import com.drones4hire.dronesapp.batchservices.tasks.JobsExpirationTask;
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

	@Autowired
	private JobsExpirationTask jobsExpirationTask;
	
	@Override
	public void executePilotNotificationTask()
	{
		try
		{
			LOGGER.info("Starting pilots notification job...");
			pilotsNotificationsTask.runTask();
			LOGGER.info("Complete pilots notification.");
		} catch (ServiceException e)
		{
			LOGGER.error("Can't run the job for notifying pilots. " + e);
		}
	}

	@Override
	public void executeJobsExpirationTask()
	{
		try
		{
			LOGGER.info("Starting job expiration job...");
			jobsExpirationTask.runTask();
			LOGGER.info("Complete jobs expiration.");
		} catch (ServiceException e)
		{
			LOGGER.error("Can't run the job for expiration jobs. " + e);
		}
	}
}