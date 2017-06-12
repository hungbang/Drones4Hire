package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.models.db.projects.Attachment;

public interface AttachmentMapper
{
	void createAttachment(Attachment attachemnt);

	void createAttachments(@Param("attachments") List<Attachment> attachments, @Param("projectId") long projectId);
	
	Attachment getAttachmentById(long id);

	List<Attachment> getAttachmentsByProjectId(long projectId);
	
	List<Attachment> getAllAttachments();

	void updateAttachment(Attachment attachment);

	void deleteAttachment(long id);
}
