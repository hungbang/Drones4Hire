package com.drones4hire.dronesapp.services.file;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;

import com.drones4hire.dronesapp.services.exceptions.ServiceException;


public abstract class FileService
{
	@Value("#{environmentProperties['drones4hire.images.dir']}")
	private String imagesDir;
	protected final static String DOT = ".";
	protected final static String DASH = "-";

	/**
	 * Save images on disk. File will be save in @see {proj}/{env}/{userId}/{type}_{uuid}.
	 * 
	 * @param inputFile
	 *            - profile foto to save in bytes.
	 * @return path to profile foto on disk.
	 */
	abstract public String saveImage(final InputStream inputFile, final String fileName, long fileSize)
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
	
	public String getImagesDir() {
		return imagesDir;
	}

	public void setImagesDir(String imagesDir) {
		this.imagesDir = imagesDir;
	}
}
