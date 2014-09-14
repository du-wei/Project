package com.webapp.webutils;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;

import com.webapp.utils.codec.KeysUtils;

//@RunWith(ParallelRunner.class) //多个测试方法同时执行
public class ContiperfUtils {

	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	/*
	 * @PerfTest invocations 执行的总次数 threads 执行的线程数 duration 执行的时间，超过该时间立即停止
	 * timer 时间对象 timerParams 等待30~80ms再执行下一次 rampUp 预热-每隔1000ms起一线程 warmUp
	 * 统计结果的时候会去掉预热的9s
	 */

	/*
	 * @Required throughput 每秒钟至少执行20次 average 测试全部完成平均执行时间为20ms median
	 * 至少50%次执行的时间在45ms内 max 所有执行的执行时间没有超过2s totalTime 测试完成执行时间少于5s percentile90
	 * 所有执行中90%的执行时间少于3s percentiles 所有执行中66%的执行时间少于200ms，96%的执行时间少于500ms
	 */

	@PerfTest(invocations = 200, threads = 20, timer = RandomTimer.class)
	@Test
	public void testName2() throws Exception {
		System.out.println(KeysUtils.shaHex("hello"));
	}

}
