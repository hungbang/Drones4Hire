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

import com.drones4hire.dronesapp.models.db.settings.NotificationSettings;
import com.drones4hire.dronesapp.models.dto.NotificationSettingsDTO;
import com.drones4hire.dronesapp.services.services.NotificationSettingService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Portfolio API")
@CrossOrigin
@RequestMapping("api/v1/account/notifications")
public class NotificationSettingsController extends AbstractController
{
	@Autowired
	private NotificationSettingService notificationService;
	
	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Get notification setting for current user", nickname = "getNotificationSettings", code = 200, httpMethod = "GET", response = NotificationSettings.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NotificationSettingsDTO getNotificationSettings()
	{
		return mapper.map(notificationService.getNotificationSettingsByUserId(getPrincipal().getId()), NotificationSettingsDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update notification settings", nickname = "updateNotificationSettings", code = 200, httpMethod = "PUT", response = NotificationSettings.class)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody NotificationSettingsDTO updateNotificationSettings(@RequestBody @Valid NotificationSettingsDTO settings)
	{
		NotificationSettings curSettings = notificationService.getNotificationSettingsByUserId(getPrincipal().getId());
		curSettings.setBidPlaced(settings.isBidPlaced());
		curSettings.setDeals(settings.isDeals());
		curSettings.setDronesNews(settings.isDronesNews());
		curSettings.setMarketing(settings.isMarketing());
		curSettings.setMonthlyNews(settings.isMonthlyNews());
		curSettings.setPaymentReceived(settings.isPaymentReceived());
		curSettings.setPlainEmail(settings.isPlainEmail());
		curSettings.setProjectAward(settings.isProjectAward());
		curSettings.setProjectUpdate(settings.isProjectUpdate());
		curSettings.setStaff(settings.isStaff());
		return mapper.map(notificationService.updateNotificationSettings(curSettings), NotificationSettingsDTO.class);
	}
}
