package com.webapp.utils.http;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class HttpToolsTest {
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	@PerfTest(invocations=2000, threads = 3, duration = 200)
	@Test
    public void testName() throws Exception {
	    String body = HttpTools.get("http://www.badiu.com").getBody();
	    System.out.println("---" + body.substring(0, 15));
    }
	
}
