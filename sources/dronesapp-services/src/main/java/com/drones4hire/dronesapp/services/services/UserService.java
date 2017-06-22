package com.drones4hire.dronesapp.services.services;

import java.util.Arrays;
import java.util.List;

import com.braintreegateway.Customer;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserCredentialsException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserStatusException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserAlreadyExistException;
import com.drones4hire.dronesapp.services.exceptions.UserNotConfirmedException;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

@Service
public class UserService
{
	private boolean DEFAULT_ENABLED = true;

	private boolean DEFAULT_CONFIRMED = true;
	
	private boolean DEFAULT_WITDRAW_ENABLED = false;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private GroupService groupService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private PilotLicenseService pilotLicenseService;

	@Autowired
	private NotificationSettingService notificationSettingService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PasswordEncryptor passwordEncryptor;
	
	@Autowired 
	private AWSEmailService emailService;

	@Autowired
	private BraintreeService braintreeService;

	@Autowired
	private PayoneerService payoneerService;
	
	@Transactional(rollbackFor=Exception.class)
	public String registerUser(User user, Role role) throws ServiceException
	{
		if(!Arrays.asList(Role.ROLE_CLIENT, Role.ROLE_PILOT).contains(role))
		{
			throw new ForbiddenOperationException("Invalid role for registration");
		}

		try
		{
			user.setEnabled(DEFAULT_ENABLED);
			user.setConfirmed(DEFAULT_CONFIRMED);
			user.setWithdrawEnabled(DEFAULT_WITDRAW_ENABLED);
			user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
			userMapper.createUser(user);
			
			// Add group with default user role
			Group group = groupService.getGroupByRole(role);
			userMapper.createUserGroup(user, group);
			user.getGroups().add(group);

			// Initialize default location
			user.setLocation(locationService.createLocation(new Location()));
			updateUser(user);

			// Initialize default notification settings
			notificationSettingService.createDefaultNotificationSettings(user);

			// Initialize default wallet
			walletService.createDefaultWallet(user);
		}
		catch(DuplicateKeyException e)
		{
			throw new UserAlreadyExistException("Duplicate user credentials");
		}

		if(Role.ROLE_PILOT.equals(role))
		{
			// Initialize default profile
			profileService.createDefaultProfile(user);

			// Initialize default license
			pilotLicenseService.createDefaultPilotLicense(user);

			// Initialize default company
			companyService.createDefaultCompany(user);
		} else if(Role.ROLE_CLIENT.equals(role))
		{
			// Initialize customer in braintree service
			Customer customer = braintreeService.createCustomer(user);
			Wallet wallet = walletService.getWalletByUserId(user.getId());
			wallet.setPaymentToken(customer.getId());
			walletService.updateWallet(wallet);
		}
		String result = payoneerService.signup(user);
		emailService.sendConfirmationEmail(user, generateConfrimEmailToken(user));
		
		return result;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void confirmUserEmail(Long id, String token) throws ServiceException
	{
		User user = getUserById(id);
		if(user != null && passwordEncryptor.checkPassword(user.getEmail(), token))
		{
			user.setConfirmed(true);
			updateUser(user);
		}
		else
		{
			throw new UserNotConfirmedException();
		}
	}
	
	@Transactional(readOnly = true)
	public User getUserById(long id) throws ServiceException
	{
		return userMapper.getUserById(id);
	}

	@Transactional
	public User createUser(User user) throws ServiceException
	{
		userMapper.createUser(user);
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws ServiceException
	{
		User user = userMapper.getUserByUsername(username);
		if(user == null)
		{
			throw new ServiceException("User with username: " + username + " not found.");
		}
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserByEmail(String email) throws ServiceException
	{
		return userMapper.getUserByEmail(email);
	}

	@Transactional(rollbackFor=Exception.class)
	public User updateUser(User user) throws ServiceException
	{
		userMapper.updateUser(user);
		return user;
	}

	@Transactional(readOnly = true)
	public SearchResult<User> search(UserSearchCriteria sc) throws ServiceException 
	{
		SearchResult<User> results = new SearchResult<>();
		results.setPage(sc.getPage());
		results.setPageSize(sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		
		List<User> users = userMapper.searchUsers(sc);
		results.setResults(users);
		results.setTotalResults(userMapper.getSearchUsersCount(sc));
		return results;
	}
	
	public User checkUserCredentials(String email, String password) throws ServiceException
	{
		User user = getUserByEmail(email);
		if(user == null || !passwordEncryptor.checkPassword(password, user.getPassword()))
		{
			throw new InvalidUserCredentialsException();
		}

		if(!user.isEnabled() || !user.isConfirmed())
		{
			throw new InvalidUserStatusException();
		}

		return user;
	}

	private String generateConfrimEmailToken(User user)
	{
		return passwordEncryptor.encryptPassword(user.getEmail());
	}
}
