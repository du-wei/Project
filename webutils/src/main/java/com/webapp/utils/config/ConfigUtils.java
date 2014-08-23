package com.webapp.utils.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.plist.PropertyListConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

public class ConfigUtils {

	private static Logger logger = Logger.getLogger(ConfigUtils.class);
	private static CompositeConfiguration composite;

	// 1。使用java.util.Properties类的load()方法
	/**
	 * @Title: read
	 * @Description: TODO helloworld
	 * @param sdfadf
	 * @return Properties adfadf
	 * @throws adsfsad
	 */
	public static Properties read(String path) {
		Properties p = new Properties();
		try (InputStream in = new BufferedInputStream(new FileInputStream(
				getPathStr(path)))) {
			p.load(in);
		} catch (IOException e) {
			logger.error(ConfigUtils.class.getSimpleName() + " 读取属性文件出错", e);
			throw new RuntimeException(ConfigUtils.class.getSimpleName()
					+ " 读取属性文件出错");
		}
		return p;
	}

	public static String getCurPath(Class<?> clz) {
		return encode(clz.getResource("")).toString();
	}

	public static String getUserPath() {
		return System.getProperty("user.dir");
	}

	public static String getClassPath() {
		return encode(getResource("/")).toString();
	}

	public static URL getPathUrl(String path) {
		return getResource(path);
	}

	public static URL getPathUrl(String path, boolean isClasspath) {
		return getResource(path, isClasspath);
	}

	public static Path getPath(String path) {
		return encode(getResource(path));
	}

	public static Path getPath(String path, boolean isClasspath) {
		return encode(getResource(path, isClasspath));
	}

	public static String getPathStr(String path) {
		return encode(getResource(path)).toString();
	}

	public static String getPathStr(String path, boolean isClasspath) {
		return encode(getResource(path, isClasspath)).toString();
	}

	public static boolean hasFileInClassPath(String path) {
		return Files.exists(Paths.get(getUserPath() + path));
	}

	public static URL getResource(String path) {
		return getResource(path, true);
	}

	public static URL getResource(Path path) {
		return encodeURL(getResource(path.toString()));
	}

	public static URL getResource(String path, boolean isClasspath) {
		// 根目录
		URL result = null;
		if (isClasspath) {
			result = ConfigUtils.class.getResource(path);
			if (result == null && !path.contains("/")) {
				result = ConfigUtils.class.getResource("/" + path);
			}
			return result;
		} else {
			try {
				result = Paths.get(path).toAbsolutePath().toUri().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return result;
		}

	}

	private static Path encode(URI url) {
		return Paths.get(url);
	}

	private static Path encode(URL url) {
		try {
			return encode(url.toURI());
		} catch (URISyntaxException e) {
			logger.error(ConfigUtils.class.getSimpleName() + " URL转换URI出错", e);
			throw new RuntimeException(ConfigUtils.class.getSimpleName()
					+ " URL转换URI出错");
		}
	}

	private static URL encodeURL(URL url) {
		try {
			return encode(url.toURI()).toUri().toURL();
		} catch (Exception e) {
			logger.error(ConfigUtils.class.getSimpleName() + " URL转换URI出错", e);
			throw new RuntimeException(ConfigUtils.class.getSimpleName()
					+ " URL转换URI出错");
		}
	}

	public static CompositeConfiguration loadDirConfig(String path,
			String... suffix) {
		Path dir = Paths.get(path);
		if (dir.toString().equals("\\") || !Files.isDirectory(dir)) {
			dir = getPath(path);
		}
		return loadDirConfig(dir, suffix);
	}

	public static CompositeConfiguration loadDirConfig(Path path,
			String... suffix) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
			for (Path entry : stream) {
				addConfig(entry.toString());
			}
		} catch (IOException e) {
			logger.error(" 配置目录 ->" + path + " 加载出错!", e);
		}
		return composite;
	}

	/*
	 * apache configuration
	 */
	public static Configuration loadAllConfig(String file) {
		DefaultConfigurationBuilder builder = null;
		try {
			builder = new DefaultConfigurationBuilder(getPathStr(file));
			return builder.getConfiguration(true);
		} catch (ConfigurationException e) {
			logger.error(" 配置文件 ->" + file + " 加载出错!", e);
		}
		return builder;
	}

	public static Configuration configConverter(Properties props) {
		return ConfigurationConverter.getConfiguration(props);
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
		if (composite == null)
			composite = new CompositeConfiguration();
		composite.addConfiguration(config);
		return composite;
	}

	public static CompositeConfiguration addSystemConfig() {
		return addConfig(new SystemConfiguration());
	}

	private static Configuration getConfig(String path) {
		try {
			if (!Paths.get(path).isAbsolute()) {
				path = getPathStr(path);
			}
			if (path.endsWith(".properties")) {
				PropertiesConfiguration config = new PropertiesConfiguration(
						path);
				config.setReloadingStrategy(getReloading());
				return config;
			} else if (path.endsWith(".xml")) {
				XMLConfiguration config = new XMLConfiguration(path);
				config.setReloadingStrategy(getReloading());
				return config;
			} else if (path.endsWith(".plist")) {
				PropertyListConfiguration config = new PropertyListConfiguration(
						path);
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

	// java system
	public static InputStream getInputStream(String url) {
		InputStream in = ConfigUtils.class.getResourceAsStream(url);
		// InputStream in5 = ClassLoader.getSystemResourceAsStream(url);
		return in;
	}

	public static ResourceBundle getResourceBundle(String url) {
		ResourceBundle rb = ResourceBundle.getBundle(url, Locale.getDefault());
		return rb;
	}

	public static ResourceBundle getResourceBundle(InputStream in) {
		ResourceBundle rb = null;
		try {
			rb = new PropertyResourceBundle(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rb;
	}

	public static String getJavaPath() {
		return System.getProperty("sun.boot.library.path");
	}

	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	public static String getJavaVmVersion() {
		return System.getProperty("java.vm.version");
	}

	public static String getJavaRunVersion() {
		return System.getProperty("java.runtime.version");
	}

	public static String getJavaPathSeparator() {
		return System.getProperty("path.separator");
	}

}
