package com.webapp.utils.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


public class BaseTest {

	private static Logger logger = LogManager.getLogger(BaseTest.class);
	public static String format = "%1$s\t%3$s -> %2$s\n";

	@Test
    public void testName() throws Exception {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		System.out.println("..");
//		Thread.sleep(1000000);
    }
	
}
