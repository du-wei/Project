package com.webapp.thread;

import org.junit.Test;

import edu.umd.cs.mtc.TestFramework;

public class MultiThreadUtils {
	
	@Test
	public void main() throws Throwable{
		MultiThread multi = new MultiThread();
		TestFramework.runOnce(multi);
	}
	
}
