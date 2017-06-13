package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.AttachmentMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Attachment.Type;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class AttachmentService
{
	@Autowired
	private AttachmentMapper attachMapper;
	
	@Autowired
	private ProjectMapper projectMapper;

	@Transactional(rollbackFor = Exception.class)
	public Attachment createAttachment(Attachment attachment, long principalId) throws ServiceException {
		checkAuthorities(attachment, principalId);
		attachMapper.createAttachment(attachment);
		return attachment;
	}

	@Transactional(rollbackFor = Exception.class)
	public void createAttachments(List<Attachment> attachments, long projectId) {
		attachMapper.createAttachments(attachments, projectId);
	}
	
	@Transactional(readOnly = true)
	public Attachment getAttachmentById(long id, long principalId) {
		return attachMapper.getAttachmentById(id);
	}

	@Transactional(readOnly = true)
	public List<Attachment> getAttachmentsByProjectId(long projectId, long principalId) {
		return attachMapper.getAttachmentsByProjectId(projectId);
	}
	
	@Transactional(readOnly = true)
	public List<Attachment> getAllAttachments(long principalId) {
		return attachMapper.getAllAttachments();
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteAttachment(long id, long principalId) throws ServiceException {
		checkAuthorities(attachMapper.getAttachmentById(id), principalId);
		attachMapper.deleteAttachment(id);
	}
	
	private void checkAuthorities(Attachment attachment, long principalId) throws ServiceException
	{
		Project project = projectMapper.getProjectById(attachment.getProjectId());
		if(attachment.getType().equals(Type.PROJECT_RESULT)) {
			if (project.getPilotId() != principalId && !project.getStatus().equals(Status.IN_PROGRESS))
			{
				throw new ForbiddenOperationException();
			}
		} else if(attachment.getType().equals(Type.PROJECT_ATTACHMENT)) {
			if (project.getClientId() != principalId)
			{
				throw new ForbiddenOperationException();
			}
		}
	}
}
