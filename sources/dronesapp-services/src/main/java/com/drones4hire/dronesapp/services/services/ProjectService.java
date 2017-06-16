package com.drones4hire.dronesapp.services.services;

import static com.drones4hire.dronesapp.models.db.projects.Project.Status.CANCELLED;
import static com.drones4hire.dronesapp.models.db.projects.Project.Status.NEW;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_CLIENT;
import static com.drones4hire.dronesapp.models.db.users.Group.Role.ROLE_PILOT;

import java.util.ArrayList;
import java.util.List;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchResult;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProjectMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.ProjectSearchCriteria;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.models.db.projects.Attachment;
import com.drones4hire.dronesapp.models.db.projects.PaidOption;
import com.drones4hire.dronesapp.models.db.projects.Project;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;

@Service
public class ProjectService
{

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

	@Transactional(rollbackFor = Exception.class)
	public Project createProject(Project project)
	{
		locationService.createLocation(project.getLocation());
		projectMapper.createProject(project);
		createProjectPaidOption(project.getId(), project.getPaidOptions());
		createAttachment(project.getId(), project.getAttachments());
		return project;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createProjectPaidOption(long projectId, List<PaidOption> paidOptions)
	{
		if(!paidOptions.isEmpty()) {
			projectMapper.createProjectPaidOption(projectId, paidOptions);
		}
		return projectId;
	}

	@Transactional(rollbackFor = Exception.class)
	public long createAttachment(long projectId, List<Attachment> attachments)
	{
		if(!attachments.isEmpty()) {
			attachmentService.createAttachments(attachments, projectId);
		}
		return projectId;
	}
	
	@Transactional(readOnly = true)
	public Project getProjectById(long id, long principalId) throws ServiceException
	{
		Project project = projectMapper.getProjectById(id);
		checkAuthorities(project, principalId);
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
			createProjectPaidOption(project.getId(), newPaidOptions);
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

	public void checkAuthorities(Project project, long principalId) throws ServiceException
	{
		User user = userService.getUserById(principalId);
		if (user.getRoles().contains(ROLE_CLIENT))
		{
			if (principalId != project.getClientId())
			{
				throw new ForbiddenOperationException();
			}
		} else if (!project.getStatus().equals(NEW) && user.getRoles().contains(ROLE_PILOT))
		{
			if (principalId != project.getPilotId())
			{
				throw new ForbiddenOperationException();
			}
		}
	}
}
