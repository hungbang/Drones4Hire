package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_ADMIN;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.BidMapper;
import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Transaction.Type;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.BidInfo;
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

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private WalletService walletService;

	@Transactional(rollbackFor = Exception.class)
	public Bid createBid(Bid bid, Long principalId) throws ServiceException
	{
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!user.getRoles().contains(ROLE_ADMIN))
			if (!project.getStatus().equals(Project.Status.NEW) || !user.getRoles().contains(ROLE_PILOT))
				throw new ForbiddenOperationException();
		bid.setUser(user);
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
		if (user.getRoles().contains(ROLE_PILOT))
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
	public BidInfo getBidInfo(long projectId) throws ServiceException
	{
		return bidMapper.getBidInfo(projectId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid updateBid(Bid bid, Long principalId) throws ServiceException
	{
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		Bid currentBid = bidMapper.getBidById(bid.getId());
		if (!user.getRoles().contains(ROLE_ADMIN))
			if (!project.getStatus().equals(Project.Status.NEW) || !user.getRoles().contains(ROLE_PILOT)
					|| !principalId.equals(currentBid.getUser().getId()))
				throw new ForbiddenOperationException();
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
		if (!user.getRoles().contains(ROLE_ADMIN))
		{
			if (!project.getStatus().equals(Project.Status.NEW) || !user.getRoles().contains(ROLE_PILOT)
					|| !principalId.equals(bid.getUser().getId()))
				throw new ForbiddenOperationException();
		} else
		{
			if (project.getStatus().equals(Status.PENDING) || project.getStatus().equals(Status.IN_PROGRESS)
					|| project.getStatus().equals(Status.NEW))
			{
				if(project.getPilotId().equals(bid.getUser().getId()))
				{
					project.setPilotId(null);
					project.setStatus(Status.NEW);
					projectService.updateProject(project);
				}
			} else
			{
				throw new ForbiddenOperationException();
			}
		}
		bidMapper.deleteBid(bidId);
		emailService.sendRetractBidEmail(project, user);
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid awardBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!user.getRoles().contains(ROLE_ADMIN))
			if (!project.getStatus().equals(Project.Status.NEW) || !user.getRoles().contains(ROLE_CLIENT)
					|| !user.getId().equals(project.getClientId()))
				throw new ForbiddenOperationException();
		User bidUser = userService.getUserById(bid.getUser().getId());
		if (!bidUser.getRoles().contains(ROLE_ADMIN))
		{
			project.setPilotId(bid.getUser().getId());
		}
		project.setStatus(Status.PENDING);
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		project.setAwardDate(Date.from(utc.toInstant()));
		projectService.updateProject(project);
		emailService.sendAwardBidEmail(project);
		//		TODO[anazarenko]: create default transaction. Remove it after payments integration.
		Transaction t = new Transaction(walletService.getWalletByUserId(principalId).getId(), bid.getAmount(),
				Currency.USD, Type.PROJECT_PAYMENT, "Award bid", project.getId(), Transaction.Status.COMPLETED);
		transactionService.createTransaction(t);
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid rewokeBid(Long bidId, Long principalId) throws ServiceException
	{
		Bid bid = bidMapper.getBidById(bidId);
		Project project = projectService.getProjectById(bid.getProjectId(), principalId);
		User user = userService.getUserById(principalId);
		if (!user.getRoles().contains(ROLE_ADMIN))
			if (!project.getStatus().equals(Project.Status.PENDING) || !user.getRoles().contains(ROLE_CLIENT)
					|| !user.getId().equals(project.getClientId()))
				throw new ForbiddenOperationException();
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
		if (!user.getRoles().contains(ROLE_ADMIN))
			if (!project.getStatus().equals(Project.Status.PENDING) || !user.getRoles().contains(ROLE_PILOT)
					|| !principalId.equals(bid.getUser().getId()))
				throw new ForbiddenOperationException();
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
		if (!user.getRoles().contains(ROLE_ADMIN))
			if (!project.getStatus().equals(Project.Status.PENDING) || user.getRoles().contains(ROLE_PILOT)
					|| !principalId.equals(bid.getUser().getId()))
				throw new ForbiddenOperationException();

		Wallet wallet = walletService.getWalletByUserId(project.getClientId());
		Transaction transaction = new Transaction(wallet.getId(), bid.getAmount(),
				Currency.USD, Type.PROJECT_REJECT, "Project reject", project.getId(), Transaction.Status.COMPLETED);
		transactionService.createTransaction(transaction);
		wallet.setBalance(wallet.getBalance().add(bid.getAmount()));
		walletService.updateWallet(wallet);

		project.setStatus(Status.NEW);
		project.setPilotId(null);
		projectService.updateProject(project);
		emailService.sendRejectBidEmail(project, user);
		return bid;
	}
}
