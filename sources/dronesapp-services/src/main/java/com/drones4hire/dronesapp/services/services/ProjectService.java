package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.payments.Transaction.Status.COMPLETED;
import static com.drones4hire.dronesapp.models.db.payments.Transaction.Type.PAID_OPTION;
import static com.drones4hire.dronesapp.models.db.projects.Project.Status.CANCELLED;
import static com.drones4hire.dronesapp.models.db.projects.Project.Status.EXPIRED;
import static com.drones4hire.dronesapp.models.db.projects.Project.Status.NEW;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.Bid;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.projects.Project.Status;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.InvalidCurrenyException;
import com.drones4hire.dronesapp.services.exceptions.PaymentException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

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
	private PaymentService paymentService;

	@Autowired
	private BidService bidService;

	@Autowired
	private PaidOptionService paidOptionService;

	@Autowired
	private AWSEmailService emailService;

	@Transactional(rollbackFor = Exception.class)
	public Project createProject(Project project) throws ServiceException
	{
		Wallet wallet = walletService.getWalletByUserId(project.getClientId());

		// Paid options selected
		String tid = null;
		if (project.hasPaidOptions())
		{
			for (PaidOption po : project.getPaidOptions())
			{
				if (!po.getCurrency().equals(wallet.getCurrency()))
				{
					throw new InvalidCurrenyException();
				}
			}
			tid = paymentService
					.makePayment(project.getPaymentMethod(), project.getPaidOptionsTotal(), wallet.getCurrency());
		}

		locationService.createLocation(project.getLocation());

		project.setSortOrder(project.calculateProjectSortOrder());
		projectMapper.createProject(project);

		if (tid != null)
		{
			transactionService.createTransaction(
					new Transaction(wallet.getId(), project.getPaidOptionsTotal(), wallet.getCurrency(), PAID_OPTION,
							tid, project.getId(), COMPLETED));
		}

		createProjectPaidOptions(project.getId(), project.getPaidOptions());

		createAttachment(project.getId(), project.getAttachments());

		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createProjectPaidOptions(long projectId, List<PaidOption> paidOptions)
	{
		if (!paidOptions.isEmpty())
		{
			projectMapper.createProjectPaidOption(projectId, paidOptions);
		}
		return projectId;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createAttachment(long projectId, List<Attachment> attachments)
	{
		if (attachments != null && !attachments.isEmpty())
		{
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
	public List<Project> getLastProjects(Date createdAfter, Status status) throws ServiceException
	{
		return projectMapper.getLastProjects(createdAfter, status);
	}

	@Transactional(readOnly = true)
	public SearchResult<ProjectSearchResult> searchProjects(ProjectSearchCriteria sc, long principalId)
			throws ServiceException
	{
		SearchResult<ProjectSearchResult> results = new SearchResult<>();
		User user = userService.getUserById(principalId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			sc.setClientId(principalId);
		} else if (user.getRoles().contains(ROLE_PILOT))
		{
			sc.setPilotId(principalId);
			if (sc.getRange() == null)
			{
				// TODO: 07/06/2017
				sc.setRange(5000);
			}
			sc.setUserCoordinates(user.getLocation().getCoordinates());
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
	public SearchResult<ProjectOnMap> searchProjectsForMap(ProjectForMapSearchCriteria sc) throws ServiceException
	{
		SearchResult<ProjectOnMap> results = new SearchResult<>();
		sc.setStatus(NEW);
		List<ProjectOnMap> projectSearchResults = projectMapper.searchProjectsForMap(sc);
		results.setTotalResults(projectMapper.getProjectsForMapSearchCount(sc));
		results.setResults(projectSearchResults);
		results.setPageSize(results.getTotalResults());
		return results;
	}

	@Transactional(readOnly = true)
	public Map<Long, Map<Status, ProjectStatisticsResult>> getProjectStatusesStatistic(ProjectSearchCriteria sc)
			throws ServiceException
	{
		Map<Long, Map<Status, ProjectStatisticsResult>> statistics = new HashMap<>();
		List<ProjectStatisticsResult> results = projectMapper.getProjectStatusesStatistic(sc);
		for (ProjectStatisticsResult result : results)
		{
			long time = result.getCreatedAt().getTime();
			if (!statistics.containsKey(time))
			{
				statistics.put(time, new HashMap<>());
				for (Status status : Project.Status.values())
				{
					statistics.get(time).put(status, new ProjectStatisticsResult(0, status));
				}
			}
			statistics.get(time).put(result.getStatus(), result);
		}
		return statistics;
	}

	@Transactional(readOnly = true)
	public List<Project> getAllProjects()
	{
		return projectMapper.getAllProjects();
	}

	@Transactional(rollbackFor = Exception.class)
	public Project updateProject(Project project) throws PaymentException, InvalidCurrenyException
	{
		Wallet wallet = walletService.getWalletByUserId(project.getClientId());

		Project existingProject = projectMapper.getProjectById(project.getId());

		// Paid options selected
		if (project.hasPaidOptions())
		{
			if (!existingProject.hasPaidOptions())
			{
				existingProject.setPaidOptions(new ArrayList<>());
			}

			// New paid options were enabled
			if (!existingProject.getPaidOptions().containsAll(project.getPaidOptions()))
			{
				project.getPaidOptions().removeAll(existingProject.getPaidOptions());
				BigDecimal total = BigDecimal.ZERO;
				for (PaidOption po : project.getPaidOptions())
				{
					PaidOption paidOption = paidOptionService.getPaidOptionById(po.getId());
					if (!paidOption.getCurrency().equals(wallet.getCurrency()))
					{
						throw new InvalidCurrenyException();
					}
					total = total.add(paidOption.getPrice());
				}
				String tid = paymentService.makePayment(project.getPaymentMethod(), total, wallet.getCurrency());
				transactionService.createTransaction(
						new Transaction(wallet.getId(), total, wallet.getCurrency(), PAID_OPTION, tid, project.getId(),
								COMPLETED));

				project.setSortOrder(project.calculateProjectSortOrder());
			}
		}

		createProjectPaidOptions(project.getId(), project.getPaidOptions());

		//createAttachment(project.getId(), project.getAttachments());

		locationService.updateLocation(project.getLocation());

		existingProject.getPaidOptions().addAll(project.getPaidOptions());

		project.setPaidOptions(existingProject.getPaidOptions());

		projectMapper.updateProject(project);

		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public void cancelProject(long id, long principalId) throws ServiceException
	{
		Project project = getProjectById(id, principalId);
		if (project == null)
		{
			throw new ServiceException("Project with id: " + id + " not found.");
		}
		if (!(project.getStatus().equals(NEW) || project.getStatus().equals(EXPIRED)))
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
		if (winningBid == null)
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

		if (!pilotWallet.getCurrency().equals(jobTransation.getCurrency()))
		{
			throw new PaymentException("Wallet currency does not match to bid currency");
		}
		pilotWallet.chageBalance(jobTotal);
		walletService.updateWallet(pilotWallet);

		project.setStatus(Project.Status.COMPLETED);
		updateProject(project);
		emailService.sendReleasePaymentEmail(project, userService.getNotNullUser(project.getPilotId()));
		emailService
				.sendSubmitPaymentEmail(project, client, transactionService.getTransactionById(jobTransation.getId()));
		return project;
	}

	private BigDecimal calculateFee(BigDecimal amount)
	{
		return amount.divide(new BigDecimal(100)).multiply(serviceFee);
	}

	public void checkAuthorities(Project project, User user) throws ServiceException
	{
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			if (!user.getId().equals(project.getClientId()))
			{
				throw new ForbiddenOperationException();
			}
		} else if (!project.getStatus().equals(NEW) && user.getRoles().contains(ROLE_PILOT))
		{
			if (!user.getId().equals(project.getPilotId()))
			{
				throw new ForbiddenOperationException();
			}
		}
	}

	public void checkStatuses(Project project, Project.Status... statuses) throws ForbiddenOperationException
	{
		if (!Arrays.asList(statuses).contains(project.getStatus()))
		{
			throw new ForbiddenOperationException("Invalid project status");
		}
	}

	public BigDecimal getServiceFee()
	{
		return serviceFee;
	}
}
