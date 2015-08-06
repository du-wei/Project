package com.webapp.utils.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.plist.PropertyListConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtils {

	private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
	private static CompositeConfiguration composite;

	public static Properties read(String path) {
		Properties p = new Properties();
		try (InputStream in = new BufferedInputStream(new FileInputStream(PathUtils.getPath(path).toString()))) {
			p.load(in);
		} catch (IOException e) {
			logger.error(PathUtils.class.getSimpleName() + " 读取属性文件出错", e);
			throw new RuntimeException(PathUtils.class.getSimpleName() + " 读取属性文件出错");
		}
		return p;
	}

	public static Properties configConverter(Configuration config) {
		return ConfigurationConverter.getProperties(config);
	}

	public static CompositeConfiguration getConfig() {
		if (composite == null) {
			composite = new CompositeConfiguration();
		}
		return composite;
	}

	public static CompositeConfiguration addDirConfig(String path, String... suffix) {
		Path dir = Paths.get(path);
		if (dir.toString().equals("\\") || !Files.isDirectory(dir)) {
			dir = PathUtils.getPath(path);
		}
		return addDirConfig(dir, suffix);
	}

	public static CompositeConfiguration addDirConfig(Path path, String... suffix) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				addConfig(entry.toString());
			}
		} catch (IOException e) {
			logger.error(" 配置目录 ->" + path + " 加载出错!", e);
		}
		return composite;
	}

	public static CompositeConfiguration addConfig(String... path) {
		for (int i = 0, k = path.length; i < k; i++) {
			addConfig(path[i]);
		}
		return composite;
	}

	public static CompositeConfiguration addConfig(String config) {
		addConfig(getConfig(config));
		return composite;
	}

	public static CompositeConfiguration addConfig(Configuration config) {
		if (composite == null) composite = new CompositeConfiguration();
		composite.addConfiguration(config);
		return composite;
	}

	public static CompositeConfiguration addSystemConfig() {
		return addConfig(new SystemConfiguration());
	}

	private static Configuration getConfig(String path) {
		try {
			if (!Paths.get(path).isAbsolute()) {
				path = PathUtils.getPath(path).toString();
			}
			if (path.endsWith(".properties")) {
				PropertiesConfiguration config = new PropertiesConfiguration(path);
				config.setReloadingStrategy(getReloading());
				return config;
			} else if (path.endsWith(".xml")) {
				XMLConfiguration config = new XMLConfiguration(path);
				config.setReloadingStrategy(getReloading());
				return config;
			} else if (path.endsWith(".plist")) {
				PropertyListConfiguration config = new PropertyListConfiguration(path);
				config.setReloadingStrategy(getReloading());
				return config;
			}

			// SystemConfiguration sys = new SystemConfiguration();
			// HierarchicalConfiguration hierarchical = new
			// HierarchicalConfiguration();
			// BaseConfiguration base = new BaseConfiguration();

		} catch (ConfigurationException e) {
			logger.error(" 配置文件 ->" + path + " 加载出错!", e);
		}
		return null;
	}

	private static FileChangedReloadingStrategy getReloading() {
		FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
		strategy.setRefreshDelay(5000);
		return strategy;
	}

}
