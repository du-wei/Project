package com.webapp.utils.config;

import org.junit.Test;

public class ConfigUtilsTest {

	@Test
    public void testName() throws Exception {
		System.out.println(PathUtils.getUserHome());
		System.out.println(PathUtils.getUserPath());
		System.out.println(PathUtils.getClassPath());
		System.out.println(PathUtils.getCurPath(ConfigUtilsTest.class));
		System.out.println(PathUtils.getPath("/redis.properties").toString());
		System.out.println(PathUtils.getPath("/", false).toString());
System.out.println();
		System.out.println(PathUtils.getResource("/"));
		System.out.println(PathUtils.getResource("/", false));

    }

}
