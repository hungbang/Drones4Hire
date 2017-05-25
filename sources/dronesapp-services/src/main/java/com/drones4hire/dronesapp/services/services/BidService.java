package com.drones4hire.dronesapp.services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.BidMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.projects.Bid;

@Service
public class BidService
{

	@Autowired
	private BidMapper bidMapper;
	
	@Autowired
	private ProjectMapper projectMapper;

	@Transactional(rollbackFor = Exception.class)
	public Bid createBid(Bid bid)
	{
		bidMapper.createBid(bid);
		return bid;
	}

	@Transactional(readOnly = true)
	public Bid getBidById(long id)
	{
		return bidMapper.getBidById(id);
	}

	@Transactional(readOnly = true)
	public List<Bid> getBidsByProjectId(long projectId)
	{
		return bidMapper.getBidsByProjectId(projectId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid updateBid(Bid bid)
	{
		bidMapper.updateBid(bid);
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteBid(long id)
	{
		bidMapper.deleteBid(id);
	}
}
