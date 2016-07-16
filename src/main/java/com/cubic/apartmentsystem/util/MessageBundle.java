package com.cubic.apartmentsystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MessageBundle {
	private static final Logger log = Logger.getLogger(MessageBundle.class);

	public static String getErrorMsg(String key) {
		return getMessage(key, "errorCodes.properties");
	}

	public static String getMessage(String key, String file) {
		try {
			Properties prop = readProperties(file);
			return prop.getProperty(key);
		} catch (Exception e) {
			log.error("Error occurred while reading file " + file, e);
		}

		return null;
	}

	public static Properties readProperties(String fileName) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream(fileName);

		Properties prop = new Properties();
		prop.load(input);

		return prop;
	}

	public static String getPropertyKey(String key, File file) {
		try {
			Properties prop = readFileAsProperty(file);
			return prop.getProperty(key);
		} catch (Exception e) {
			log.error("Error occurred while reading file " + file, e);
		}

		return null;
	}

	public static Properties readFileAsProperty(File filename) throws IOException {
		InputStream stream = new FileInputStream(filename);
		Properties prop = new Properties();
		prop.load(stream);
		return prop;
	}
}
