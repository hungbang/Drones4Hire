package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.MessageMapper;
import com.drones4hire.dronesapp.models.db.Message;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class MessageService
{
	@Autowired
	private MessageMapper messageMapper;

	@Transactional(rollbackFor = Exception.class)
	public Message createMessage(Message message) throws ServiceException
	{
		messageMapper.createMessage(message);
		return getMessageById(message.getId());
	}

	@Transactional(readOnly = true)
	public Message getMessageById(long id)
	{
		return messageMapper.getMessageById(id);
	}

	@Transactional(readOnly = true)
	public List<Message> getMessagesByFromUserId(long userId) throws ServiceException
	{
		return messageMapper.getMessagesByFromUserId(userId);
	}
	
	@Transactional(readOnly = true)
	public List<Message> getMessagesByToUserId(long userId) throws ServiceException
	{
		return messageMapper.getMessagesByToUserId(userId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Message updateMessage(Message message, long principalId) throws ServiceException
	{
		messageMapper.updateMessage(message);
		return message;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteMessage(long id)
	{
		messageMapper.deleteMessage(id);
	}
}
