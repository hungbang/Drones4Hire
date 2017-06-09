package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.BidInfoSearchCriteria;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.BidInfo;

public interface BidMapper
{
	void createBid(Bid bid);

	Bid getBidById(long id);

	List<Bid> getBidsByProjectId(Long projectId);

	Bid getBidByProjectIdAndUserId(Long projectId, Long userId);

	List<BidInfo> getBidInfosByClientId(BidInfoSearchCriteria sc);

	void updateBid(Bid bid);

	void deleteBid(long id);
}
