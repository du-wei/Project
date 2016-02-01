package com.webapp.utils.spring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

public final class CtxResourceUtils {

	private static final Logger logger = LoggerFactory.getLogger(CtxResourceUtils.class);
	public static File getFile(String cfg){
		File file = null;
		try {
			file = ResourceUtils.getFile(cfg);
		} catch (FileNotFoundException e) {
			logger.error("", e);
		}
		return file;
	}
	public static Resource getResource(String cfg) {
		Resource rource = new ClassPathResource(cfg);
		return rource;
	}

	public static Properties getProperties(String cfg) {
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(getResource(cfg));
		} catch (IOException e) {
			logger.error("", e);
		}
		return props;
	}

	public static ResourceBundle getResourceBundle(String cfg) {
		if(cfg.contains(".")) cfg = cfg.split("\\.")[0];
		ResourceBundle bundle = MessageSourceResourceBundle.getBundle(cfg);
		return bundle;
	}

}
