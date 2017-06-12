package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.BidInfoSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.projects.BidInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.BidMapper;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

@Service
public class BidService
{

	@Autowired
	private BidMapper bidMapper;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;
	
	@Autowired 
	private AWSEmailService emailService;

	@Transactional(rollbackFor = Exception.class)
	public Bid createBid(Bid bid, Long principalId) throws ServiceException
	{
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.NEW) || user.getRoles().contains(ROLE_PILOT)
				|| !user.getId().equals(bid.getUserId()))
			new ForbiddenOperationException();
		bidMapper.createBid(bid);
		emailService.sendNewBidReceiveEmail(project, user);
		emailService.sendNewBidPlacedEmail(project, user);
		return bid;
	}

	@Transactional(readOnly = true)
	public Bid getBidById(long id)
	{
		return bidMapper.getBidById(id);
	}

	@Transactional(readOnly = true)
	public List<Bid> getBidsByProjectId(long projectId, long principalId) throws ServiceException
	{
		User user = userService.getUserById(principalId);
		Long pilotId = null;
		if(user.getRoles().contains(ROLE_PILOT))
		{
			pilotId = principalId;
		}
		return bidMapper.getBidsByProjectIdAndPilotId(projectId, pilotId);
	}

	@Transactional(readOnly = true)
	public Bid getBidByProjectIdAndUserId(long projectId, long userId)
	{
		return bidMapper.getBidByProjectIdAndUserId(projectId, userId);
	}

	@Transactional(readOnly = true)
	public SearchResult<BidInfo> getBidInfos(BidInfoSearchCriteria sc, Long principalId) throws ServiceException
	{
		SearchResult<BidInfo> results = new SearchResult<>();
		results.setSortOrder(sc.getSortOrder());
		results.setPageSize(sc.getPageSize());
		results.setPage(sc.getPage());
		User user = userService.getUserById(principalId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			sc.setClientId(principalId);
		} else if (user.getRoles().contains(ROLE_PILOT))
		{
			sc.setPilotId(principalId);
		}
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		List<BidInfo> bidInfos = bidMapper.searchBidInfos(sc);
		for(BidInfo bidInfo : bidInfos)
		{
			Long pilotId = projectService.getProjectById(bidInfo.getProjectId(), sc.getClientId()).getPilotId();
			if(pilotId == null)
			{
				bidInfo.setEndDate(bidInfo.getCreatedAt());
			} else
			{
				bidInfo.setBidAmount(getBidByProjectIdAndUserId(bidInfo.getProjectId(), pilotId).getAmount());
				bidInfo.setEndDate(bidInfo.getModifiedAt());
			}
		}
		results.setResults(bidInfos);
		results.setTotalResults(bidMapper.getBidInfosCount(sc));
		return results;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid updateBid(Bid bid, Long principalId) throws ServiceException
	{
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.NEW) || user.getRoles().contains(ROLE_PILOT)
				|| !user.getId().equals(bid.getUserId()))
			new ForbiddenOperationException();
		Bid currentBid = bidMapper.getBidById(bid.getId());
		currentBid.setComment(bid.getComment());
		currentBid.setAmount(bid.getAmount());
		currentBid.setCurrency(bid.getCurrency());
		bidMapper.updateBid(bid);
		emailService.sendUpdateBidEmail(project, user);
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.NEW) || user.getRoles().contains(ROLE_PILOT)
				|| !user.getId().equals(bid.getUserId()))
			new ForbiddenOperationException();
		bidMapper.deleteBid(bidId);
		emailService.sendRetractBidEmail(project, user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bid awardBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.NEW) || user.getRoles().contains(ROLE_CLIENT)
				|| !user.getId().equals(project.getClientId()))
			new ForbiddenOperationException();
		project.setPilotId(bid.getUserId());
		project.setStatus(Status.PENDING);
		projectService.updateProject(project);
		emailService.sendAwardBidEmail(project);
		return bid;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bid rewokeBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.PENDING) || user.getRoles().contains(ROLE_CLIENT)
				|| !user.getId().equals(project.getClientId()))
			new ForbiddenOperationException();
		project.setPilotId(null);
		project.setStatus(Status.NEW);
		projectService.updateProject(project);
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid acceptBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.PENDING) || user.getRoles().contains(ROLE_PILOT)
				|| !user.getId().equals(bid.getUserId()))
			new ForbiddenOperationException();
		project.setStatus(Status.IN_PROGRESS);
		projectService.updateProject(project);
		emailService.sendAcceptBidEmail(project, user);
		return bid;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bid rejectBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!project.getStatus().equals(Project.Status.PENDING) || user.getRoles().contains(ROLE_PILOT)
				|| !user.getId().equals(bid.getUserId()))
			new ForbiddenOperationException();
		project.setStatus(Status.NEW);
		project.setPilotId(null);
		projectService.updateProject(project);
		emailService.sendRejectBidEmail(project, user);
		return bid;
	}
}
