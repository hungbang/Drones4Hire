package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Attachment.Type;

public interface AttachmentMapper
{
	void createAttachment(Attachment attachemnt);

	void createAttachments(@Param("attachments") List<Attachment> attachments, @Param("projectId") long projectId);
	
	Attachment getAttachmentById(long id);

	List<Attachment> getAttachmentsByProjectId(long projectId);
	
	List<Attachment> getAttachmentsByProjectIdAndType(@Param("projectId") long projectId, @Param("type") Type type);
	
	List<Attachment> getAllAttachments();

	void deleteAttachment(long id);
}
