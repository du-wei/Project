package com.webapp.junit.junit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.junit.Test;

public class JunitAssertThat extends TestCase {

	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("king", "king");

		// assertThat( 8, allOf( greaterThan(1), lessThan(15) ) );
		// assertThat( 8, anyOf( greaterThan(16), lessThan(8) ) );
		assertThat(8, anything());
		assertThat("king", is("king"));
		assertThat("king", not("kin"));
		assertThat("king", containsString("king"));
		assertThat("king", endsWith("king"));
		assertThat("king", startsWith("king"));
		assertThat("king", equalTo("king"));
		assertThat("king", equalToIgnoringCase("king"));
		assertThat("king", equalToIgnoringWhiteSpace("king"));
		assertThat(3.0, closeTo(3.0, 0.3));
		assertThat(4.0, greaterThan(3.0));
		assertThat(2.0, lessThan(10.0));
		assertThat(6.0, greaterThanOrEqualTo(5.0));
		assertThat(2.0, lessThanOrEqualTo(16.0));
		assertThat(map, hasEntry("king", "king"));
		assertThat(map, hasKey("king"));
		assertThat(map, hasValue("king"));
		// assertThat( iterable, hasItem ( "king" ) );
	}

	public static void main(String[] args) {
		TestRunner.run(JunitAssertThat.class);
	}

}
