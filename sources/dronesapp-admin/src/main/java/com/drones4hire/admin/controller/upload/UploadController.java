package com.drones4hire.admin.controller.upload;

import com.drones4hire.admin.controller.AbstractController;
import com.drones4hire.dronesapp.models.aws.FileUploadObject;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.file.impl.AmazonFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
public class UploadController extends AbstractController
{

	@Autowired
	private AmazonFileService fileService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("FileType") FileUploadObject.Type type) throws
			ServiceException
	{
		FileUploadObject fileObject = new FileUploadObject(getPrincipal().getId(), file, type);
		return String.format("{\"url\": \"%s\"}", fileService.saveImage(fileObject));
	}
}
