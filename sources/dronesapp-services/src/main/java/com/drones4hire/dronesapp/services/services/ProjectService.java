package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.payments.Transaction.Status.COMPLETED;
import static com.drones4hire.dronesapp.models.db.payments.Transaction.Type.PAID_OPTION;
import static com.drones4hire.dronesapp.models.db.projects.Project.Status.CANCELLED;
import static com.drones4hire.dronesapp.models.db.projects.Project.Status.NEW;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteriaForAdmin;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.commons.Currency;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.PaymentException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class ProjectService
{
	@Value("${drones4hire.service.fee}")
	private BigDecimal serviceFee;
	
	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private PaidOptionService paidOptionService;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private BidService bidService;

	@Transactional(rollbackFor = Exception.class)
	public Project createProject(Project project) throws ServiceException
	{
		Wallet wallet = walletService.getWalletByUserId(project.getClientId());
		
		BigDecimal total = project.getPaidOptionsTotal();
		// Paid options selected
		if(!BigDecimal.ZERO.equals(total))
		{
			Currency currency = project.getPaidOptions().get(0).getCurrency();
			String trId = paymentService.makePayment(project.getPaymentMethod(), total, currency);
			transactionService.createTransaction(new Transaction(wallet.getId(), total, currency, PAID_OPTION, trId, project.getId(), COMPLETED));
		}
		
		locationService.createLocation(project.getLocation());
		
		projectMapper.createProject(project);
		
		createProjectPaidOptions(project.getId(), project.getPaidOptions());
		
		createAttachment(project.getId(), project.getAttachments());
		
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createProjectPaidOptions(long projectId, List<PaidOption> paidOptions)
	{
		if(!paidOptions.isEmpty()) {
			projectMapper.createProjectPaidOption(projectId, paidOptions);
		}
		return projectId;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createAttachment(long projectId, List<Attachment> attachments)
	{
		if(attachments != null && !attachments.isEmpty()) {
			attachmentService.createAttachments(attachments, projectId);
		}
		return projectId;
	}
	
	@Transactional(readOnly = true)
	public Project getProjectById(long id) throws ServiceException
	{
		return projectMapper.getProjectById(id);
	}
	
	@Transactional(readOnly = true)
	public Project getProjectById(long id, long userId) throws ServiceException
	{
		Project project = projectMapper.getProjectById(id);
		checkAuthorities(project, userService.getUserById(userId));
		return project;
	}

	@Transactional(readOnly = true)
	public SearchResult<ProjectSearchResult> searchProjects(ProjectSearchCriteria sc, long principalId) throws ServiceException
	{
		SearchResult<ProjectSearchResult> results = new SearchResult<>();
		User user = userService.getUserById(principalId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			sc.setClientId(principalId);
		} else if (user.getRoles().contains(ROLE_PILOT))
		{
			sc.setPilotId(principalId);
		}
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		List<ProjectSearchResult> projectSearchResults = projectMapper.searchProjects(sc);
		results.setTotalResults(projectMapper.getProjectsSearchCount(sc));
		results.setResults(projectSearchResults);
		return results;
	}

	@Transactional(readOnly = true)
	public SearchResult<ProjectSearchResult> searchProjectsWithAdmin(ProjectSearchCriteriaForAdmin sc) throws ServiceException
	{
		SearchResult<ProjectSearchResult> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		List<ProjectSearchResult> projectSearchResults = projectMapper.searchProjectsWithAdmin(sc);
		results.setTotalResults(projectMapper.getProjectsWithAdminSearchCount(sc));
		results.setResults(projectSearchResults);
		return results;
	}

	@Transactional(readOnly = true)
	public List<Project> getAllProjects()
	{
		return projectMapper.getAllProjects();
	}

	@Transactional(rollbackFor = Exception.class)
	public Project updateProject(Project project)
	{
		locationService.updateLocation(project.getLocation());
		Project dbProject = projectMapper.getProjectById(project.getId());
		List<PaidOption> newPaidOptions = new ArrayList<>();
		Transaction transaction = null;
		for(PaidOption paidOption: project.getPaidOptions())
		{
			paidOption = paidOptionService.getPaidOptionById(paidOption.getId());
			if(! dbProject.getPaidOptions().contains(paidOption))
			{
				transaction = new Transaction();
				transaction.setCurrency(paidOption.getCurrency());
				transaction.setPurpose("Add paid option '" + paidOption.getTitle() + "'");
				transaction.setStatus(Transaction.Status.COMPLETED);
				transaction.setProjectId(project.getId());
				transaction.setType(Transaction.Type.PAID_OPTION);
				transaction.setAmount(paidOption.getPrice());
				transaction.setWalletId(walletService.getWalletByUserId(dbProject.getClientId()).getId());
				transactionService.createTransaction(transaction);
				newPaidOptions.add(paidOption);
			}
		}
		if(newPaidOptions.size() != 0)
		{
			createProjectPaidOptions(project.getId(), newPaidOptions);
		}
		dbProject.getPaidOptions().addAll(newPaidOptions);
		project.setPaidOptions(dbProject.getPaidOptions());
		projectMapper.updateProject(project);
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public void cancelProject(long id, long principalId) throws ServiceException
	{
		Project project = getProjectById(id, principalId);
		if(project == null)
		{
			throw new ServiceException("Project with id: " + id + " not found.");
		}
		if(! project.getStatus().equals(NEW))
		{
			throw new ForbiddenOperationException();
		}
		project.setStatus(CANCELLED);
		projectMapper.updateProject(project);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProject(long id)
	{
		projectMapper.deleteProject(id);
	}
	
	@Transactional(readOnly = true)
	public Integer getProjectsSearchCount(ProjectSearchCriteria sc) throws ServiceException 
	{
		return projectMapper.getProjectsSearchCount(sc);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Project releasePayment(Long projectId, Long userId) throws ServiceException
	{
		User client = userService.getNotNullUser(userId);
		
		Project project = getProjectById(projectId, userId);
		checkAuthorities(project, client);
		checkStatuses(project, Status.IN_PROGRESS);
		
		Bid winningBid = bidService.getBidByProjectIdAndUserId(projectId, project.getPilotId());
		if(winningBid == null)
		{
			throw new ForbiddenOperationException("No winning bid found");
		}
		
		BigDecimal feeTotal = calculateFee(winningBid.getAmount());
		BigDecimal jobTotal = winningBid.getAmount().subtract(feeTotal);
		
		Wallet pilotWallet = walletService.getNotNullUserWallet(project.getPilotId());
		
		Transaction feeTransation = new Transaction(pilotWallet.getId(), 
				feeTotal, winningBid.getCurrency(), Transaction.Type.SERVICE_FEE, null, 
				projectId, Transaction.Status.COMPLETED);
		transactionService.createTransaction(feeTransation);
		
		Transaction jobTransation = new Transaction(pilotWallet.getId(), 
									jobTotal, winningBid.getCurrency(), Transaction.Type.PAYMENT_RELEASED, null, 
									projectId, Transaction.Status.COMPLETED);
		transactionService.createTransaction(jobTransation);
		
		if(pilotWallet.getCurrency().equals(jobTransation.getCurrency()))
		{
			throw new PaymentException("Wallet currency does not match to bid currency");
		}
		pilotWallet.chageBalance(jobTotal);
		walletService.updateWallet(pilotWallet);
		
		project.setStatus(Project.Status.COMPLETED);
		return updateProject(project);
	}
	
	private BigDecimal calculateFee(BigDecimal amount)
	{
		return amount.divide(serviceFee).multiply(new BigDecimal(100));
	}
	
	public void checkAuthorities(Project project, User user) throws ServiceException
	{
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			if (user.getId() != project.getClientId())
			{
				throw new ForbiddenOperationException();
			}
		} else if (!project.getStatus().equals(NEW) && user.getRoles().contains(ROLE_PILOT))
		{
			if (user.getId() != project.getPilotId())
			{
				throw new ForbiddenOperationException();
			}
		}
	}
	
	
	public void checkStatuses(Project project, Project.Status ... statuses) throws ForbiddenOperationException
	{
		if(!Arrays.asList(statuses).contains(project.getStatus()))
		{
			throw new ForbiddenOperationException("Invalid project status");
		}
	}
}
