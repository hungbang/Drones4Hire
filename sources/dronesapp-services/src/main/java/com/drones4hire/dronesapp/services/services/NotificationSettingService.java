package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.NotificationSettingsMapper;
import com.drones4hire.dronesapp.models.db.settings.NotificationSettings;
import com.drones4hire.dronesapp.models.db.users.User;

@Service
public class NotificationSettingService
{

	@Autowired
	private NotificationSettingsMapper notificationSettingsMapper;

	@Transactional(rollbackFor = Exception.class)
	public NotificationSettings createNotificationSettings(NotificationSettings notificationSettings)
	{
		notificationSettingsMapper.createNotificationSettings(notificationSettings);
		return notificationSettings;
	}

	@Transactional(readOnly = true)
	public NotificationSettings getNotificationSettingsById(long id)
	{
		return notificationSettingsMapper.getNotificationSettingsById(id);
	}

	@Transactional(readOnly = true)
	public NotificationSettings getNotificationSettingsByUserId(long id)
	{
		return notificationSettingsMapper.getNotificationSettingsByUserId(id);
	}
	
	@Transactional(readOnly = true)
	public List<NotificationSettings> getAllNotificationSettings()
	{
		return notificationSettingsMapper.getAllNotificationSettings();
	}

	@Transactional(rollbackFor = Exception.class)
	public NotificationSettings updateNotificationSettings(NotificationSettings notificationSettings)
	{
		notificationSettingsMapper.updateNotificationSettings(notificationSettings);
		return notificationSettings;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteNotificationSettings(long id)
	{
		notificationSettingsMapper.deleteNotificationSettings(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public NotificationSettings createDefaultNotificationSettings(User user)
	{
		NotificationSettings ns = new NotificationSettings();
		ns.setUserId(user.getId());
		return createNotificationSettings(ns);
	}
}
