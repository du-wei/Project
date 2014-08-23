package com.webapp.junit.junit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JunitTestBase {

	@Before
	// 每一个测试方法之前运行
	public void before() {
		p("===========before");
	}

	@After
	// 每一个测试方法之后运行
	public void after() {
		p("===========after");
	}

	@BeforeClass
	// 所有测试开始之前运行
	public static void beforeClass() {
		p("beforeClass");
	}

	@Test
	public void testJunit() {
		int n = 15;
		String str = "googleadmin";
		assertThat(n, allOf(greaterThan(1), lessThan(16)));
		// assertThat(n, anyOf(greaterThan(16), lessThan(8)));
		assertThat(n, anything());
		assertThat(str, is("googleadmin"));
		// assertThat(str, not("googleadmin"));

		assertThat(str, containsString("googleadmin"));
		assertThat(str, endsWith("googleadmin"));
		assertThat(str, startsWith("googleadmin"));
		assertThat(n, equalTo(15));
		assertThat(str, equalToIgnoringCase("googleadmin"));
		assertThat(str, equalToIgnoringWhiteSpace("googleadmin"));

		double d = 3.0;
		assertThat(d, closeTo(3.0, 0.3));
		assertThat(d, greaterThan(3.0));
		assertThat(d, lessThan(10.0));
		assertThat(d, greaterThanOrEqualTo(5.0));
		assertThat(d, lessThanOrEqualTo(16.0));

		Map<String, String> map = new HashMap<String, String>();
		map.put("google", "admin");

		Iterator<String> iterable = map.keySet().iterator();

		// assertThat(map, hasEntry("google", "admin"));
		// assertThat( iterable, hasItem ( "bjsxt" ) );
		// assertThat( map, hasKey ( "google" ) );
		// assertThat( map, hasValue ( "admin" ) );

		p("hello");
	}

	// @Ignore
	@Test
	public void testIgnore() {
		p("ignore not print");
	}

	@Test(expected = NullPointerException.class, timeout = 20)
	public void test1() {
		p("ignore not print");
	}

	@AfterClass
	// 所有测试结束之后运行
	public static void afterClass() {
		p("afterClass");
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}
