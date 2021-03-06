package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.BidInfoSearchCriteria;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.BidInfo;
import org.apache.ibatis.annotations.Param;

public interface BidMapper
{
	void createBid(Bid bid);

	Bid getBidById(long id);

	List<Bid> getBidsByProjectIdAndPilotId(@Param("projectId") Long projectId, @Param("pilotId") Long pilotId);

	Bid getBidByProjectIdAndUserId(@Param("projectId") Long projectId, @Param("userId") Long userId);

	BidInfo getBidInfo(Long projectId);

	Integer getBidInfosCount(BidInfoSearchCriteria sc);

	void updateBid(Bid bid);

	void deleteBid(long id);
}
