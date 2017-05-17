package com.drones4hire.dronesapp.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.drones4hire.dronesapp.models.aws.FileUploadObject;
import com.drones4hire.dronesapp.models.aws.FileUploadObject.Type;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.file.impl.AmazonFileService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "Uploading files API")
@Controller
@RequestMapping("api/v1/upload")
public class UploadingController extends AbstractController
{
	@Autowired
	private AmazonFileService fileService;

	@ResponseStatusDetails
	@ApiOperation(value = "Upload file", nickname = "uploadFile", code = 200, httpMethod = "POST", response = String.class)
	@ResponseStatus(HttpStatus.OK)
	@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", paramType = "header"), 
			@ApiImplicitParam(name = "FileType", paramType = "header")})
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("FileType") Type type) throws ServiceException
	{
		FileUploadObject fileObject = new FileUploadObject(getPrincipal().getId(), file, type);
		return fileService.saveImage(fileObject);
	}
}
