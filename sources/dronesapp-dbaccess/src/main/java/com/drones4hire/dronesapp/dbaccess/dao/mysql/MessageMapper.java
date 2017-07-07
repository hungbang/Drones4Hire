package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.Message;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper
{
	void createMessage(Message message);

	Message getMessageById(long id);
	
	List<Message> getMessagesByFromUserId(Long userId);

	List<Message> getMessagesByToUserId(Long userId);

	List<Message> getMessagesByUserId(Long userId);

	List<Message> getMessagesByProjectIdAndUserId(@Param("projectId") Long projectId, @Param("userId") Long userId);

	void updateMessage(Message message);

	void deleteMessage(long id);
}
