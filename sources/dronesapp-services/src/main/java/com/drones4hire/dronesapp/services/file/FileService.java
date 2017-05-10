package com.drones4hire.dronesapp.services.file;


import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.drones4hire.dronesapp.models.db.User;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;


public abstract class FileService
{
	protected final static String DOT = ".";
	protected final static String DASH = "-";

	/**
	 * Save images on disk. File will be save in @see {env}/{userId}/{uuid}.
	 * 
	 * @param inputFile
	 *            - profile foto to save in bytes.
	 * @return path to profile foto on disk.
	 */
	abstract public String saveImage(MultipartFile file, User user)
			throws ServiceException;

	/**
	 * Get file by URL.
	 * 
	 * @param pathToFile
	 *            - path to file for removing.
	 * @return path to car foto on disk.
	 */
	abstract public InputStream getFile(String pathToFile)
			throws ServiceException;

	/**
	 * Remove file from disk.
	 * 
	 * @param pathToFile
	 *            - path to file for removing.
	 * @throws ServiceException
	 *             if can't remove file.
	 */
	abstract public void removeFile(String pathToFile) throws ServiceException;
	
}
