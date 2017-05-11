package com.drones4hire.dronesapp.services.file.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.AWSException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import com.drones4hire.dronesapp.services.file.FileService;

public class AmazonFileService extends FileService
{
	@Autowired
	private AmazonS3 s3Client;
	@Value("#{environmentProperties['drones4hire.files.amazon.bucket']}")
	private String bucket;
	@Value("#{environmentProperties['drones4hire.env']}")
	private String env;

	private final static String SEPARATOR = "/";
	private final static String URL_SEPARATOR = "\\?";

	/*
	 * @see com.copper.services.file.FileUploader#saveProfileImage(byte[])
	 */
	@Override
	public String saveImage(MultipartFile file, User user) throws ServiceException
	{
		return saveFile(file, getImageDir(user));
	}

	/*
	 * @see com.copper.services.file.FileUploader#removeFile(java.lang.String)
	 */
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
	
	/*
	 * @see com.copper.dbaccess.file.FileUploader#removeFile(java.lang.String)
	 */
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

	private String getImageDir(User user) {
		String path = env + SEPARATOR + user.getId();
//		ObjectListing listing = s3Client.listObjects(bucket, "env/");
//		for (String folders : listing.getCommonPrefixes()) {
//			if(folders.contains(String.valueOf(user.getId()))) {
//				return path;
//			}
//		}
//		CreateBucketRequest request = new CreateBucketRequest(path);
//		s3Client.createBucket(request);
		return path;
	}
	
	private String saveFile(MultipartFile file, final String dir) throws ServiceException
	{
		String hash = UUID.randomUUID().toString().replaceAll(DASH, StringUtils.EMPTY);
		String filePath = dir + SEPARATOR + hash + DOT + FilenameUtils.getExtension(file.getOriginalFilename());
		SdkBufferedInputStream stream = null;
		try {
			stream = new SdkBufferedInputStream(file.getInputStream(), (int) (file.getSize() + 100));
			String type = Mimetypes.getInstance().getMimetype(file.getOriginalFilename());
	
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(type);
			metadata.setContentLength(file.getSize());
	
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
}
