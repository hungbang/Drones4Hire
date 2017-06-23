package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.Message;

public interface MessageMapper
{
	void createMessage(Message message);

	Message getMessageById(long id);
	
	List<Message> getMessagesByFromUserId(Long userId);

	List<Message> getMessagesByToUserId(Long userId);

	void updateMessage(Message message);

	void deleteMessage(long id);
}
