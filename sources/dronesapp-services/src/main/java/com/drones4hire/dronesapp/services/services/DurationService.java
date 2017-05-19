package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.DurationMapper;
import com.drones4hire.dronesapp.models.db.commons.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DurationService
{

	@Autowired
	private DurationMapper durationMapper;

	@Transactional(rollbackFor = Exception.class)
	public Duration createDuration(Duration duration)
	{
		durationMapper.createDuration(duration);
		return duration;
	}

	@Transactional(readOnly = true)
	public Duration getDurationById(long id)
	{
		return durationMapper.getDurationById(id);
	}

	@Transactional(readOnly = true)
	public List<Duration> getAllDurations()
	{
		return durationMapper.getAllDurations();
	}

	@Transactional(rollbackFor = Exception.class)
	public Duration updateDuration(Duration duration)
	{
		durationMapper.updateDuration(duration);
		return duration;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteDuration(long id)
	{
		durationMapper.deleteDuration(id);
	}
}
