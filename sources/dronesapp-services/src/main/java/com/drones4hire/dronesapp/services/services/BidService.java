package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.BidMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.TransactionSearchCriteria;
import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Transaction.Type;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
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
	
	@Autowired
	private PaymentService paymentService;

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
	public Bid createBid(Bid bid, Long userId) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		projectService.checkAuthorities(project, user);
		projectService.checkStatuses(project, Project.Status.NEW);
		
		bid.setUser(user);
		bidMapper.createBid(bid);
		
		emailService.sendNewBidReceiveEmail(project);
		
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid updateBid(Bid bid, Long userId) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		projectService.checkAuthorities(project, user);
		projectService.checkStatuses(project, Project.Status.NEW);
		
		Bid currentBid = bidMapper.getBidById(bid.getId());
		currentBid.setComment(bid.getComment());
		currentBid.setAmount(bid.getAmount());
		currentBid.setCurrency(bid.getCurrency());
		bidMapper.updateBid(bid);

		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteBid(Long bidId, Long userId) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Bid bid = bidMapper.getBidById(bidId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		
		if(bid.getUser().getId() != user.getId() || project.getPilotId() == user.getId())
		{
			throw new ForbiddenOperationException("Unable to delete bid");
		}
		
		bidMapper.deleteBid(bidId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid awardBid(Long bidId, Long userId, String paymentMethod) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Wallet wallet = walletService.getNotNullUserWallet(user.getId());
		
		Bid bid = bidMapper.getBidById(bidId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		projectService.checkAuthorities(project, user);
		projectService.checkStatuses(project, Project.Status.NEW);
		
		String trId = paymentService.authorizePayment(paymentMethod, bid.getAmount(), bid.getCurrency());
		Transaction transaction = new Transaction(wallet.getId(), bid.getAmount(), Currency.USD, Type.PROJECT_PAYMENT, trId, project.getId(), Transaction.Status.AUTHORIZED);
		transactionService.createTransaction(transaction);
		
		project.setPilotId(bid.getUser().getId());
		project.setStatus(Status.PENDING);
		project.setAwardDate(Calendar.getInstance().getTime());
		projectService.updateProject(project);
		
		emailService.sendAwardBidEmail(project);
		
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid revokeBid(Long bidId, Long userId) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Bid bid = bidMapper.getBidById(bidId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		projectService.checkAuthorities(project, user);
		projectService.checkStatuses(project, Project.Status.PENDING);
		
		TransactionSearchCriteria sc = new TransactionSearchCriteria();
		sc.setProjectId(project.getId());
		sc.setType(Type.PROJECT_PAYMENT);
		
		for(Transaction transaction : transactionService.searchTransactions(sc).getResults())
		{
			paymentService.release(transaction.getPurpose());
			transaction.setStatus(Transaction.Status.VOIDED);
			transactionService.updateTransaction(transaction);
		}
		
		project.setPilotId(null);
		project.setStatus(Status.NEW);
		projectService.updateProject(project);
		
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid acceptBid(Long bidId, Long userId) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Bid bid = bidMapper.getBidById(bidId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		projectService.checkAuthorities(project, user);
		projectService.checkStatuses(project, Project.Status.PENDING);
		
		TransactionSearchCriteria sc = new TransactionSearchCriteria();
		sc.setProjectId(project.getId());
		sc.setType(Type.PROJECT_PAYMENT);
		sc.setStatus(Transaction.Status.AUTHORIZED);
		
		for(Transaction transaction : transactionService.searchTransactions(sc).getResults())
		{
			paymentService.settle(transaction.getPurpose());
			transaction.setStatus(Transaction.Status.COMPLETED);
			transactionService.updateTransaction(transaction);
		}
		
		project.setStatus(Status.IN_PROGRESS);
		projectService.updateProject(project);
		
		emailService.sendAcceptBidEmail(project, user);
		
		return bid;
	}

	@Transactional(rollbackFor = Exception.class)
	public Bid rejectBid(Long bidId, Long userId) throws ServiceException
	{
		User user = userService.getNotNullUser(userId);
		
		Bid bid = bidMapper.getBidById(bidId);
		
		Project project = projectService.getProjectById(bid.getProjectId());
		projectService.checkAuthorities(project, user);
		projectService.checkStatuses(project, Project.Status.PENDING);
		
		TransactionSearchCriteria sc = new TransactionSearchCriteria();
		sc.setProjectId(project.getId());
		sc.setType(Type.PROJECT_PAYMENT);
		sc.setStatus(Transaction.Status.AUTHORIZED);
		
		for(Transaction transaction : transactionService.searchTransactions(sc).getResults())
		{
			paymentService.release(transaction.getPurpose());
			transaction.setStatus(Transaction.Status.VOIDED);
			transactionService.updateTransaction(transaction);
		}
		
		project.setPilotId(null);
		project.setStatus(Status.NEW);
		projectService.updateProject(project);
		
		emailService.sendRejectBidEmail(project, user, bid);
		
		return bid;
	}
}
