package com.webapp.utils.http;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;

public class HttpToolsTest {
	
	@Rule
	public ContiPerfRule rule = new ContiPerfRule();
	
	@PerfTest(invocations=20, threads = 1, duration = 200, timer = RandomTimer.class, timerParams = {
		30, 80 }, rampUp = 1000, warmUp = 9000)
	@Test
    public void testName() throws Exception {
	    String body = HttpTools.get("http://www.badiu.com").getBody();
	    System.out.println("---" + body.substring(0, 15));
    }
	
}
