package com.drones4hire.dronesapp.batchservices.services;

public interface ISchedulerService
{
	 void executePilotNotificationTask();

	 void executeJobsExpirationTask();
}
