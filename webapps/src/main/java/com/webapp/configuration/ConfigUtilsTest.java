package com.webapp.configuration;

import org.junit.Test;

import com.webapp.utils.config.ConfigUtils;

public class ConfigUtilsTest {

	@Test
	public void testConfig() {

		// Configuration config = ConfigUtils.loadAllConfig("config.xml");
		// System.out.println(config.getString("username"));
		// System.out.println(config.getString("name"));
		// System.out.println(config.getString("girls.girl(1)"));

		// ConfigUtils.addConfig("app.properties");
		// ConfigUtils.addConfig("app.xml");
		// ConfigUtils.addSystemConfig();
		// CompositeConfiguration c = ConfigUtils.getConfig();
		// System.out.println(c.getString("username"));
		// System.out.println(c.getString("girls.girl(1)"));
		// System.out.println(c.getString("user.dir"));
		// System.out.println(ConfigUtils.getPathStr("log4j.properties"));
		// ConfigUtils.loadDirConfig("/", "properties");

		System.out.println(ConfigUtils.getClassPath());
		System.out.println(ConfigUtils.getCurPath(ConfigUtilsTest.class));
		System.out.println(ConfigUtils.getUserPath());
		System.out.println(ConfigUtils.getPathStr("/"));

	}

}
