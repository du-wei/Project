package com.webapp.utils.config;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathUtils {

	private static Logger logger = LogManager.getLogger(PathUtils.class);

	public static String getCurPath(Class<?> clz) {
		return encode(clz.getResource("")).toString();
	}

	public static String getUserPath() {
		return System.getProperty("user.dir");
	}

	public static String getClassPath() {
		return encode(getResource("/")).toString();
	}

	public static Path getPath(String path) {
		return encode(getResource(path));
	}

	public static Path getPath(String path, boolean isClasspath) {
		return encode(getResource(path, isClasspath));
	}

	public static boolean isExist(String path) {
		return Files.exists(Paths.get(getUserPath() + path));
	}

	public static URL getResource(String path) {
		return getResource(path, true);
	}

	public static URL getResource(String path, boolean isClasspath) {
		// 根目录
		URL result = null;
		if (isClasspath) {
			result = PathUtils.class.getResource(path);
			if (result == null && !path.contains("/")) {
				result = PathUtils.class.getResource("/" + path);
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
			logger.error(PathUtils.class.getSimpleName() + " URL转换URI出错", e);
			throw new RuntimeException(PathUtils.class.getSimpleName()
					+ " URL转换URI出错");
		}
	}


	public static String getJavaPath() {
		return System.getProperty("sun.boot.library.path");
	}

	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	public static String getJavaVersion() {
		return System.getProperty("java.runtime.version");
	}

}
