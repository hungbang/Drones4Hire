package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.Feedback;

public interface FeedbackMapper
{
	void createFeedback(Feedback feedback);

	Feedback getFeedbackById(long id);

	List<Feedback> getFeedbacksByProjectId(Long projectId);
	
	List<Feedback> getFeedbacksByUserId(Long userId);

	void updateFeedback(Feedback feedback);

	void deleteFeedback(long id);
}
