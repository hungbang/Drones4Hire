package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.settings.NotificationSettings;

public interface NotificationSettingsMapper
{
	void createNotificationSettings(NotificationSettings settings);

	NotificationSettings getNotificationSettingsById(long id);
	
	NotificationSettings getNotificationSettingsByUserId(long userId);

	List<NotificationSettings> getAllNotificationSettings();

	void updateNotificationSettings(NotificationSettings settings);

	void deleteNotificationSettings(long id);
}
