package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.models.db.projects.Bid;

public interface BidMapper
{
	void createBid(Bid bid);

	Bid getBidById(long id);

	List<Bid> getBidsByProjectId(Long projectId);

	void updateBid(Bid bid);

	void deleteBid(long id);
}
