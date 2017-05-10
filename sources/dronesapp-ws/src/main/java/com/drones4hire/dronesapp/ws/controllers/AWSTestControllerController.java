package com.drones4hire.dronesapp.ws.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.drones4hire.dronesapp.models.db.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.file.impl.AmazonFileService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

@Controller
@RequestMapping("api/v1/aws")
public class AWSTestControllerController extends AbstractController {

	@Autowired
	private AmazonFileService fService;
	
	@ResponseStatusDetails
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createUser(@RequestParam("file") MultipartFile file) throws ServiceException, IOException
	{
		User u = new User();
		u.setId(1L);
		String url = fService.saveImage(file, u);
		return url;
	}
}
