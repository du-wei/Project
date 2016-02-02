package com.webapp.thread;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

public class MultiThreadUtils {

	public static void name(MultithreadedTestCase multiThread) throws Throwable {
		TestFramework.runOnce(multiThread);
	}

}
