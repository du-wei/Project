package com.webapp.junit.junitperf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.clarkware.junitperf.ConstantTimer;
import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TestMethodFactory;
import com.clarkware.junitperf.TimedTest;
import com.clarkware.junitperf.Timer;

/**
 * @ClassName: JunitPerfUtils.java
 * @Package com.webapp.junit.junitperf
 * @Description: TODO 时间和压力测试
 * @author king king
 * @date 2013年10月26日 下午9:54:06
 * @version V1.0
 */
public class JunitPerfUtils {

	private static TestSuite suite = new TestSuite();
	private Map<Class<?>, List<String>> clzMethods = new HashMap<>();
	private ThreadLocal<Class<?>> local = new ThreadLocal<>();

	public void testRun() {
		TestRunner.run(suite);
	}

	public void testTime(long maxTime) {
		testTime(getArrayPara(maxTime));
	}

	public void testTime(long[] maxTime) {
		addTimeSuite(clzMethods, maxTime);
	}

	public void testLoad(int users, long delay) {
		testLoad(getArrayPara(users), getArrayPara(delay));
	}

	public void testLoad(int[] users, long[] delay) {
		addLoadSuite(clzMethods, users, delay);
	}

	public void testLoadAndTime(long maxTime, int users, long delay) {
		testLoadAndTime(getArrayPara(maxTime), getArrayPara(users),
				getArrayPara(delay));
	}

	public void testLoadAndTime(long[] maxTime, int[] users, long[] delay) {
		addTimeAndLoadSuite(clzMethods, maxTime, users, delay);
	}

	public JunitPerfUtils addMethods(Class<?> clz, String... methods) {
		if (this.clzMethods.containsKey(clz)) {
			List<String> list = this.clzMethods.get(clz);
			list.addAll(Arrays.asList(methods));
		} else {
			this.clzMethods.put(clz, Arrays.asList(methods));
		}
		setCurClz(clz);
		return this;
	}

	private TestSuite addTimeSuite(Class<?> clz, String methods, long maxTime) {
		Test test = getTest(clz, methods);
		Test timeTest = getTimeTest(test, maxTime);
		suite.addTest(timeTest);
		return suite;
	}

	private TestSuite addTimeSuite(Class<?> clz, String[] methods,
			long[] maxTime) {
		for (int i = 0; i < methods.length; i++) {
			addTimeSuite(clz, methods[i], maxTime[i]);
		}
		return suite;
	}

	private TestSuite addTimeSuite(Map<Class<?>, List<String>> methods,
			long[] maxTime) {
		Iterator<Class<?>> iterator = methods.keySet().iterator();
		while (iterator.hasNext()) {
			Class<?> clz = iterator.next();
			addTimeSuite(clz, methods.get(clz).toArray(new String[] {}),
					maxTime);
		}
		return suite;
	}

	private TestSuite addLoadSuite(Class<?> clz, String methods, int users,
			long delay) {
		Test test = getTest(clz, methods);
		Test loadTest = getLoadTest(test, users, delay);
		suite.addTest(loadTest);
		return suite;
	}

	private TestSuite addLoadSuite(Class<?> clz, String[] methods, int[] users,
			long[] delay) {
		for (int i = 0; i < methods.length; i++) {
			addLoadSuite(clz, methods[i], users[i], delay[i]);
		}
		return suite;
	}

	private TestSuite addLoadSuite(Map<Class<?>, List<String>> methods,
			int[] users, long[] delay) {
		Iterator<Class<?>> iterator = methods.keySet().iterator();
		while (iterator.hasNext()) {
			Class<?> clz = iterator.next();
			addLoadSuite(clz, methods.get(clz).toArray(new String[] {}), users,
					delay);
		}
		return suite;
	}

	private TestSuite addTimeAndLoadSuite(Class<?> clz, String methods,
			long maxTime, int users, long delay) {
		Test test = getTest(clz, methods);
		Test timeTest = getTimeTest(test, maxTime);
		Test loadTest = getLoadTest(timeTest, users, delay);
		suite.addTest(loadTest);
		return suite;
	}

	private TestSuite addTimeAndLoadSuite(Class<?> clz, String[] methods,
			long[] maxTime, int[] users, long[] delay) {
		for (int i = 0; i < methods.length; i++) {
			addTimeAndLoadSuite(clz, methods[i], maxTime[i], users[i], delay[i]);
		}
		return suite;
	}

	private TestSuite addTimeAndLoadSuite(Map<Class<?>, List<String>> methods,
			long[] maxTime, int[] users, long[] delay) {
		Iterator<Class<?>> iterator = methods.keySet().iterator();
		while (iterator.hasNext()) {
			Class<?> clz = iterator.next();
			addTimeAndLoadSuite(clz, methods.get(clz).toArray(new String[] {}),
					maxTime, users, delay);
		}
		return suite;
	}

	private Test getTimeTest(Test test, long maxTime) {
		Test timeTest = new TimedTest(test, maxTime);
		return timeTest;
	}

	private Test getLoadTest(Test test, int users, long delay) {
		Timer timer = new ConstantTimer(delay);
		Test loadTest = new LoadTest(test, users, timer);
		return loadTest;
	}

	private Test getTest(Class<?> clz, String methods) {
		Test test = new TestMethodFactory(clz, methods);
		return test;
	}

	private long[] getArrayPara(long para) {
		Class<?> clz = local.get();
		List<String> methods = clzMethods.get(clz);
		long[] paras = new long[methods.size()];
		for (int i = 0; i < methods.size(); i++) {
			paras[i] = para;
		}
		return paras;
	}

	private int[] getArrayPara(int para) {
		Class<?> clz = local.get();
		List<String> methods = clzMethods.get(clz);
		int[] paras = new int[methods.size()];
		for (int i = 0; i < methods.size(); i++) {
			paras[i] = para;
		}
		return paras;
	}

	private void setCurClz(Class<?> clz) {
		Class<?> isClz = local.get();
		if (isClz == null) {
			local.set(clz);
		}
	}
}
