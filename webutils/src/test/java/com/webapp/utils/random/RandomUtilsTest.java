package com.webapp.utils.random;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

public class RandomUtilsTest {

	String format = "%1$s\t%3$s -> %2$s\n";

	@Test
    public void testName() throws Exception {
	    int result = RandomUtils.nextInt();
	    System.out.printf(format ,"", result, "nextInt()");

	    result = RandomUtils.nextInt(5);
	    System.out.printf(format ,"", result, "nextInt(bound)");
	    assertThat(result, Matchers.lessThan(5));

	    result = RandomUtils.nextInt(5, 10);
	    System.out.printf(format ,"", result, "nextInt(int least, bound)");
	    assertThat(result, Matchers.greaterThanOrEqualTo(5));
	    assertThat(result, Matchers.lessThan(10));
    }

}
