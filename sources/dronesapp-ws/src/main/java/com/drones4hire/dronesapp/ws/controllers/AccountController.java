package com.drones4hire.dronesapp.ws.controllers;

import static com.drones4hire.dronesapp.services.services.notifications.AbstractEmailService.CHANGE_PASSWORD_PATH;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.services.Service;
import com.drones4hire.dronesapp.models.db.users.Company;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.PilotLocation;
import com.drones4hire.dronesapp.models.db.users.Profile;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.models.dto.AccountDTO;
import com.drones4hire.dronesapp.models.dto.ChangePasswordDTO;
import com.drones4hire.dronesapp.models.dto.CompanyDTO;
import com.drones4hire.dronesapp.models.dto.PilotLicenseDTO;
import com.drones4hire.dronesapp.models.dto.PilotLocationDTO;
import com.drones4hire.dronesapp.models.dto.ProfileDTO;
import com.drones4hire.dronesapp.models.dto.WalletDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.services.CompanyService;
import com.drones4hire.dronesapp.services.services.LocationService;
import com.drones4hire.dronesapp.services.services.PilotLicenseService;
import com.drones4hire.dronesapp.services.services.PilotLocationService;
import com.drones4hire.dronesapp.services.services.ProfileService;
import com.drones4hire.dronesapp.services.services.ServiceService;
import com.drones4hire.dronesapp.services.services.UserService;
import com.drones4hire.dronesapp.services.services.WalletService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	private PilotLocationService pilotLocationService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private PilotLicenseService licenseService;

	@Autowired
	private ProfileService profileService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get user account", nickname = "getUserAccount", code = 200, httpMethod = "GET", response = AccountDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountDTO getUserAccount() throws MappingException, ServiceException
	{
		WalletDTO wallet = mapper.map(walletService.getWalletByUserId(getPrincipal().getId()), WalletDTO.class);
		AccountDTO account = mapper.map(userService.getUserById(getPrincipal().getId()), AccountDTO.class);
		account.setWallet(wallet);
		return account;
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
		user.setFlightHours(account.getFlightHours());

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
	public @ResponseBody PilotLicenseDTO updateLicense(@RequestBody @Valid PilotLicenseDTO license) throws ServiceException
	{
		PilotLicense curLicense = licenseService.getPilotLicenseByUserId(getPrincipal().getId());
		curLicense.setInsuranceURL(license.getInsuranceURL());
		curLicense.setLicenseURL(license.getLicenseURL());
		curLicense.setVerified(license.isVerified());
		return mapper.map(licenseService.updatePilotLicense(curLicense), PilotLicenseDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Change password", nickname = "changePassword", code = 200, httpMethod = "PUT")
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "password/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void changePassword(@Valid @RequestBody ChangePasswordDTO changePassword) throws ServiceException
	{
		userService.changeUserPassword(getPrincipal().getId(), changePassword.getCurrentPassword(), changePassword.getNewPassword());
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Change public profile", nickname = "updateProfile", code = 200, httpMethod = "PUT", response = ProfileDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "profile", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProfileDTO updateProfile(@Valid @RequestBody ProfileDTO p) throws ServiceException
	{
		Profile profile = null;
		if(getPrincipal().getAuthorities().contains(ADMIN))
		{
			profile = profileService.getProfileByUserId(p.getId());
		}
		else
		{
			profile = profileService.getProfileByUserId(getPrincipal().getId());
		}
		profile.setTagline(p.getTagline());
		profile.setBio(p.getBio());
		profile.setWebURL(p.getWebURL());
		profile.setCompanyLogoURL(p.getCompanyLogoURL());
		profile.setCoverPhotoURL(p.getCoverPhotoURL());
		return mapper.map(profileService.updateProfile(profile), ProfileDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Create pilot location", nickname = "createPilotLocation", code = 201, httpMethod = "POST", response = PilotLocationDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "locations", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLocationDTO createPilotLocation(@Valid @RequestBody PilotLocationDTO pl)
	{
		PilotLocation pilotLocation = mapper.map(pl, PilotLocation.class);
		pilotLocation.setUserId(getPrincipal().getId());
		return mapper.map(pilotLocationService.createPilotLocation(pilotLocation), PilotLocationDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get pilot locations", nickname = "getPilotLocations", code = 200, httpMethod = "GET", response = List.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "locations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PilotLocationDTO> getPilotLocations()
	{
		List<PilotLocation> pilotLocations = pilotLocationService.getPilotLocationsByUserId(getPrincipal().getId());
		List<PilotLocationDTO> pilotLocationDTOs = new ArrayList<>();
		for(PilotLocation pilotLocation : pilotLocations)
		{
			pilotLocationDTOs.add(mapper.map(pilotLocation, PilotLocationDTO.class));
		}
		return pilotLocationDTOs;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Change pilot location", nickname = "changePilotLocation", code = 200, httpMethod = "PUT", response = PilotLocationDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "locations", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLocationDTO changePilotLocation(@Valid @RequestBody PilotLocationDTO pl) throws ServiceException
	{
		PilotLocation pilotLocation = pilotLocationService.getPilotLocationById(pl.getId());
		checkPrincipalPermissions(pilotLocation.getUserId());
		pilotLocation.setOffice(pl.getOffice());
		pilotLocation.setPhone(pl.getPhone());
		pilotLocation.setAlternativePhone(pl.getAlternativePhone());
		pilotLocation.setLocation(mapper.map(pl.getLocation(), Location.class));
		return mapper.map(pilotLocationService.updatePilotLocation(pilotLocation), PilotLocationDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete pilot location", nickname = "deletePilotLocation", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({"ROLE_PILOT", "ROLE_ADMIN"})
	@RequestMapping(value = "locations/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePilotLocation(@ApiParam(value = "Id of the pilot location", required = true) @PathVariable(value = "id") long id) throws ForbiddenOperationException
	{
		PilotLocation pilotLocation = pilotLocationService.getPilotLocationById(id);
		checkPrincipalPermissions(pilotLocation.getUserId());
		pilotLocationService.deletePilotLocation(pilotLocation);
	}
}
