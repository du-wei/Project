package com.webapp.junit.junitperf;

import junit.framework.TestCase;

import org.junit.Test;

import com.clarkware.junitperf.ExampleTestCase;

public class JunitPerfTest extends TestCase {

	public JunitPerfTest(String name) {
		super(name);
	}

	public void test() throws Exception {
		Thread.sleep(5);
	}

	@Test
	public void test1() {

		JunitPerfUtils utils = new JunitPerfUtils();
		utils.addMethods(JunitPerfTest.class, "test").testLoadAndTime(20, 200,
				0);
		utils.testRun();
	}

	public void ok() {
		ExampleTestCase case1 = new ExampleTestCase("");
	}

}
