package com.drones4hire.dronesapp.ws.controllers;

import static com.drones4hire.dronesapp.services.services.notifications.AbstractEmailService.CHANGE_PASSWORD_PATH;

import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.services.Service;
import com.drones4hire.dronesapp.models.db.users.Company;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.AccountDTO;
import com.drones4hire.dronesapp.models.dto.ChangeEmailDTO;
import com.drones4hire.dronesapp.models.dto.CompanyDTO;
import com.drones4hire.dronesapp.models.dto.PilotLicenseDTO;
import com.drones4hire.dronesapp.models.dto.auth.ChangePasswordDTO;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.CompanyService;
import com.drones4hire.dronesapp.services.services.LocationService;
import com.drones4hire.dronesapp.services.services.PilotLicenseService;
import com.drones4hire.dronesapp.services.services.ServiceService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.auth.JWTService;
import com.drones4hire.dronesapp.services.services.notifications.AWSEmailService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Account API")
@CrossOrigin
@RequestMapping("api/v1/account")
public class AccountController extends AbstractController
{
	@Autowired
	private UserService userService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private PilotLicenseService licenseService;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AWSEmailService emailService;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get user account", nickname = "getUserAccount", code = 200, httpMethod = "GET", response = User.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountDTO getUserAccount() throws MappingException, ServiceException
	{
		return mapper.map(userService.getUserById(getPrincipal().getId()), AccountDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user account", nickname = "updateUserAccount", code = 200, httpMethod = "PUT", response = AccountDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountDTO updateUserAccount(@Valid @RequestBody AccountDTO account) throws ServiceException
	{
		User user = userService.getUserById(getPrincipal().getId());
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setPhotoURL(account.getPhotoURL());
		user.setIntroduction(account.getIntroduction());
		user.setSummary(account.getSummary());

		Location location = mapper.map(account.getLocation(), Location.class);
		location.setId(user.getLocation().getId());
		user.setLocation(locationService.updateLocation(location));

		return mapper.map(userService.updateUser(user), AccountDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get user company", nickname = "getUserCompany", code = 200, httpMethod = "GET", response = CompanyDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "company", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CompanyDTO getUserCompany()
	{
		return mapper.map(companyService.getCompanyByUserId(getPrincipal().getId()), CompanyDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user company", nickname = "updateUserCompany", code = 200, httpMethod = "PUT", response = CompanyDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "company", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CompanyDTO updateUserCompany(@Valid @RequestBody CompanyDTO c)
			throws ServiceException
	{
		Company company = companyService.getCompanyById(c.getId());
		checkPrincipalPermissions(company.getUserId());

		company.setName(c.getName());
		company.setWebURL(c.getWebURL());
		company.setContactName(c.getContactName());
		company.setContactEmail(c.getContactEmail());
		company.setCountry(c.getCountry());

		return mapper.map(companyService.updateCompany(company), CompanyDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get account services", nickname = "getAccountServices", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Service> getAccountServices()
	{
		return serviceService.getServicesByUserId(getPrincipal().getId());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update account service", nickname = "updateAccountService", code = 200, httpMethod = "PUT", response = List.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "services", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Service> updateAccountService(@RequestBody List<Long> serviceIds)
	{
		return serviceService.updateUserServices(getPrincipal().getId(), serviceIds);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get license for current pilot", nickname = "getLicense", code = 200, httpMethod = "GET", response = PilotLicenseDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "license", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLicenseDTO getLicense()
	{
		return mapper.map(licenseService.getPilotLicenseByUserId(getPrincipal().getId()), PilotLicenseDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update pilot license", nickname = "updatePilotLicense", code = 200, httpMethod = "PUT", response = PilotLicenseDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "license", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLicenseDTO updateLicense(@RequestBody @Valid PilotLicenseDTO license)
	{
		PilotLicense curLicense = licenseService.getPilotLicenseByUserId(getPrincipal().getId());
		curLicense.setInsuranceURL(license.getInsuranceURL());
		curLicense.setLicenseURL(license.getLicenseURL());
		curLicense.setVerified(license.isVerified());
		return mapper.map(licenseService.updatePilotLicense(curLicense), PilotLicenseDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update user email", nickname = "updateUserEmail", code = 200, httpMethod = "PUT")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "email", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserEmail(@RequestBody ChangeEmailDTO email)
			throws ServiceException
	{
		User user = userService.getUserById(getPrincipal().getId());
		user = userService.checkUserCredentials(user.getEmail(), email.getPassword());
		user.setEmail(email.getEmail());
		emailService.sendChangingEmail(user, jwtService.generateEmailToken(user.getId(), email.getEmail()));
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Change password", nickname = "changePassword", code = 200, httpMethod = "PUT")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = CHANGE_PASSWORD_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void changePassword(@Valid @RequestBody ChangePasswordDTO changePassword) throws ServiceException
	{
		User user = userService.getUserById(getPrincipal().getId());
		user.setPassword(passwordEncryptor.encryptPassword(changePassword.getPassword()));
		userService.updateUser(user);
	}
}
