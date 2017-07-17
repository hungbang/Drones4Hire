package com.drones4hire.dronesapp.services.services;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import com.drones4hire.dronesapp.services.services.util.CSVWriter;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.braintreegateway.Customer;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.SearchResult;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.UserSearchCriteria;
import com.drones4hire.dronesapp.models.db.Question;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Group.Role;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.EmailNotVerifiedException;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserCredentialsException;
import com.drones4hire.dronesapp.services.exceptions.InvalidUserStatusException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.exceptions.UserAlreadyExistException;
import com.drones4hire.dronesapp.services.exceptions.UserNotFoundException;
import com.drones4hire.dronesapp.services.services.auth.JWTService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;

@Service
public class UserService
{
	private boolean DEFAULT_ENABLED = true;

	private boolean DEFAULT_CONFIRMED = false;
	
	private Double DEAFULT_RATING = 5.0;
	
	private static final Integer DEFAULT_RANGE = 1000;

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
	private JWTService jwtService;
	
	@Transactional(rollbackFor=Exception.class)
	public User registerUser(User user, Role role) throws ServiceException
	{
		if(!Arrays.asList(Role.ROLE_CLIENT, Role.ROLE_PILOT).contains(role))
		{
			throw new ForbiddenOperationException("Invalid role for registration");
		}

		checkUniqueFields(user);

		try
		{
			user.setEnabled(DEFAULT_ENABLED);
			user.setConfirmed(DEFAULT_CONFIRMED);
			user.setRating(DEAFULT_RATING);
			user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
			userMapper.createUser(user);
		}
		catch(DuplicateKeyException e)
		{
			throw new UserAlreadyExistException("Duplicate user credentials");
		}
			
		// Add group with default user role
		Group group = groupService.getGroupByRole(role);
		userMapper.createUserGroup(user, group);
		user.getGroups().add(group);

		// Initialize default location
		if(user.getLocation() != null)
		{
			locationService.createLocation(user.getLocation());
		}
		else
		{
			user.setLocation(locationService.createLocation(new Location()));
		}
		updateUser(user);

		// Initialize default notification settings
		notificationSettingService.createDefaultNotificationSettings(user);

		// Initialize default wallet
		walletService.createDefaultWallet(user);
		

		if(Role.ROLE_PILOT.equals(role))
		{
			// Initialize default range
			user.getLocation().setRange(DEFAULT_RANGE);
			locationService.updateLocation(user.getLocation());
			// Initialize default profile
			profileService.createDefaultProfile(user);
			// Initialize default license
			pilotLicenseService.createDefaultPilotLicense(user);
		}


		if(user.getRoles().contains(Role.ROLE_CLIENT) || user.getRoles().contains(Role.ROLE_PILOT))
		{
			// Initialize default company
			companyService.createDefaultCompany(user);
		}
		
		// Initialize customer in braintree service
		Customer customer = braintreeService.createCustomer(user);
		Wallet wallet = walletService.getWalletByUserId(user.getId());
		wallet.setPaymentToken(customer.getId());
		walletService.updateWallet(wallet);
		
		emailService.sendConfirmationEmail(user, jwtService.generateConfirmEmailToken(user));
		
		return user;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void confirmUserEmail(String token) throws ServiceException
	{
		User user = getNotNullUser(jwtService.readConfirmEmailToken(token).getId());
		if(user.isConfirmed())
		{
			throw new ForbiddenOperationException("User already confirmed");
		}
		
		user.setConfirmed(true);
		updateUser(user);
		if(user.getRoles().contains(Role.ROLE_CLIENT))
			emailService.sendClientEmailConfirmedEmail(user);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void resetUserPassword(String token, String newPassword) throws ServiceException
	{
		User user = getNotNullUser(jwtService.readConfirmEmailToken(token).getId());
		user.setPassword(passwordEncryptor.encryptPassword(newPassword));
		updateUser(user);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void changeUserPassword(long userId, String currentPassword, String newPassword) throws ServiceException
	{
		User user = getNotNullUser(userId);
		if(!passwordEncryptor.checkPassword(currentPassword, user.getPassword()))
		{
			throw new ForbiddenOperationException();
		}
		user.setPassword(passwordEncryptor.encryptPassword(newPassword));
		updateUser(user);
	}
	
	@Transactional(readOnly = true)
	public User getUserById(long id) throws ServiceException
	{
		return userMapper.getUserById(id);
	}
	
	@Transactional(readOnly = true)
	public User getNotNullUser(long id) throws ServiceException
	{
		User user = userMapper.getUserById(id);
		if(user == null)
		{
			throw new UserNotFoundException("Unable to find user by ID: " + id);
		}
		return user;
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
	
	@Transactional(readOnly = true)
	public List<User> getUsersNearLocation(Location location, Role role, Integer unit) throws ServiceException
	{
		return userMapper.getUsersNearLocation(location, role, unit);
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
		sc.setPageSizeFully(sc.getPage(), sc.getPageSize());
		results.setSortOrder(sc.getSortOrder());
		
		List<User> users = userMapper.searchUsers(sc);
		results.setResults(users);
		results.setTotalResults(userMapper.getSearchUsersCount(sc));
		return results;
	}

	@Transactional(readOnly = true)
	public void exportUsersToCSV(UserSearchCriteria sc, Writer writer)
			throws ServiceException, IOException
	{
		SearchResult<User> results = new SearchResult<>();
		sc.setPage(null);
		sc.setPageSize(null);

		List<User> users = userMapper.searchUsers(sc);
		if(!sc.getRole().equals(Role.ROLE_PILOT))
		{
			for(User user : users)
			{
				user.setRating(null);
			}
		}
		results.setResults(users);
		CSVWriter.exportUsersToCSV(results, writer);
	}
	
	@Transactional(readOnly = true)
	public Integer getUserCount(UserSearchCriteria sc) throws ServiceException 
	{
		return userMapper.getSearchUsersCount(sc);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void sendQuestion(Question question)
	{
		emailService.sendQuestionEmail(question);
	}
	
	public User checkUserCredentials(String email, String password) throws ServiceException
	{
		User user = getUserByEmail(email);
		if(user == null || !passwordEncryptor.checkPassword(password, user.getPassword()))
		{
			throw new InvalidUserCredentialsException();
		}

		if(!user.isEnabled())
		{
			throw new InvalidUserStatusException();
		}
		
		if(!user.isConfirmed())
		{
			emailService.sendUpConfirmationEmail(user, jwtService.generateConfirmEmailToken(user));
			throw new EmailNotVerifiedException();
		}

		return user;
	}

	private void checkUniqueField(String fieldName, User u) throws UserAlreadyExistException
	{
		User user = null;
		switch(fieldName)
		{
		case "email":
			user = userMapper.getUserByEmail(u.getEmail());
			break;
		case "username":
			user = userMapper.getUserByUsername(u.getUsername());
			break;
		default:
			break;
		}
		if(user != null)
		{
			throw new UserAlreadyExistException("Current " + fieldName + " already exists");
		}
	}

	private void checkUniqueFields(User user) throws UserAlreadyExistException
	{
		checkUniqueField("email", user);
		checkUniqueField("username", user);
	}
}
