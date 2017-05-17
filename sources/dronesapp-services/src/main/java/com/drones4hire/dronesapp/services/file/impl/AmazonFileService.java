package com.drones4hire.dronesapp.services.file.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.internal.SdkBufferedInputStream;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.drones4hire.dronesapp.models.aws.FileUploadObject;
import com.drones4hire.dronesapp.services.exceptions.AWSException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.file.FileService;

public class AmazonFileService extends FileService
{
	@Autowired
	private AmazonS3 s3Client;
	@Value("#{environmentProperties['drones4hire.amazon.bucket']}")
	private String bucket;
	@Value("#{environmentProperties['drones4hire.env']}")
	private String env;

	private final static String SEPARATOR = "/";
	private final static String URL_SEPARATOR = "\\?";

	@Override
	public String saveImage(FileUploadObject fileObject) throws ServiceException
	{
		String filePath = getFilePath(fileObject);
		MultipartFile multipartFile = fileObject.getFile();
		SdkBufferedInputStream stream = null;
		try {
			stream = new SdkBufferedInputStream(multipartFile.getInputStream(), (int) (multipartFile.getSize() + 100));
			String type = Mimetypes.getInstance().getMimetype(multipartFile.getOriginalFilename());
	
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(type);
			metadata.setContentLength(multipartFile.getSize());
	
			PutObjectRequest putRequest = new PutObjectRequest(bucket, filePath, stream, metadata);
			s3Client.putObject(putRequest);
			s3Client.setObjectAcl(bucket, filePath, CannedAccessControlList.PublicRead);
			
		} catch (IOException e) {
			throw new AWSException("Can't save file to Amazone", e);
		} finally {
			IOUtils.closeQuietly(stream);
		}
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, filePath);
		return s3Client.generatePresignedUrl(request).toString().split(URL_SEPARATOR)[0];
	}

	@Override
	public void removeFile(final String pathToFile) throws ServiceException
	{
		try
		{
			s3Client.deleteObject(new DeleteObjectRequest(bucket, new URL(pathToFile).getPath().substring(1)));
		} catch (MalformedURLException e)
		{
			throw new ServiceException(e);
		}
	}
	
	@Override
	public InputStream getFile(final String pathToFile) throws ServiceException
	{
		try
		{ 
			S3Object object = s3Client.getObject(new GetObjectRequest(bucket, new URL(pathToFile).getPath().substring(1)));
			InputStream objectData = object.getObjectContent();
			return objectData;
		} catch (MalformedURLException e)
		{
			throw new ServiceException(e);
		}
	}
	
	private String getFilePath(FileUploadObject file) {
		String dir = env + SEPARATOR + file.getUserId();
		MultipartFile multipartFile = file.getFile();
		String hash = file.getType() + DASH + UUID.randomUUID().toString().replaceAll(DASH, StringUtils.EMPTY);
		return dir + SEPARATOR + hash + DOT + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
	}
}
