package com.drones4hire.dronesapp.ws.controllers;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.dto.PilotLicenseDTO;
import com.drones4hire.dronesapp.services.services.PilotLicenseService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Pilot license API")
@CrossOrigin
@RequestMapping("api/v1/account/license")
public class PilotLicenseController extends AbstractController
{
	@Autowired
	private PilotLicenseService licenseService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get license for current pilot", nickname = "getLicense", code = 200, httpMethod = "GET", response = PilotLicenseDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLicenseDTO getLicense()
	{
		return mapper.map(licenseService.getPilotLicenseByUserId(getPrincipal().getId()), PilotLicenseDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update pilot license", nickname = "updatePilotLicense", code = 200, httpMethod = "PUT", response = PilotLicenseDTO.class)
	@ApiImplicitParams(
	{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotLicenseDTO updateLicense(@RequestBody @Valid PilotLicenseDTO license)
	{
		PilotLicense curLicense = licenseService.getPilotLicenseByUserId(getPrincipal().getId());
		curLicense.setInsuranceURL(license.getInsuranceURL());
		curLicense.setLicenseURL(license.getLicenseURL());
		curLicense.setVerified(license.isVerified());
		return mapper.map(licenseService.updatePilotLicense(curLicense), PilotLicenseDTO.class);
	}
}
