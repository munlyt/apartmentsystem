package com.cubic.apartmentsystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cubic.apartmentsystem.exception.BusinessException;

public class Utility {
	public static final String ROOT_FOLDER = System.getProperty("catalina.home");
	public static final String APARTMENT_SYSTEM_DOCS_FOLDER = "/apartment_system_docs";
	public static final String RESOURCES = "/resources";
	public static final String IMAGES = "/images/";

	public static final String IMAGE_UPLOAD_PATH = Utility.ROOT_FOLDER + Utility.APARTMENT_SYSTEM_DOCS_FOLDER
			+ Utility.RESOURCES + Utility.IMAGES;

	private static Logger logger = Logger.getLogger(Utility.class);

	public static void logError(Logger log, String message, Exception e) {
		if (e instanceof BusinessException)
			logger.info(e.getMessage());
		else
			logger.error(message, e);
	}

	public static String encryptPassword(String password) {
		if (password != null && !password.isEmpty()) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder.encode(password);
		}
		return null;
	}

	public static Boolean matchPassword(String rawPassword, String dbEncryptedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, dbEncryptedPassword);
	}

	public static String getRandomString() {
		return UUID.randomUUID().toString();
	}

	public static String getLoggedInUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			return userDetails.getUsername();
		}
		return null;
	}

	public static Date getCurrentDateTime() {
		return new Date();
	}

	public static byte[] getImageFromPath(String path) {
		// save image into database
		File file = new File(path);
		byte[] bFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}
	
	public static void main(String[] args){
		System.out.println(encryptPassword("password"));
	}

}
