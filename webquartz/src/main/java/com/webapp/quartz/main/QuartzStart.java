/**   
 * @Title: QuartzStart.java 
 * @Package com.webapp.scheduled.main 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-1 下午3:24:29 
 * @version V1.0   
 */
package com.webapp.quartz.main;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: QuartzStart.java
 * @Package com.webapp.scheduled.main
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-1 下午3:24:29
 * @version V1.0
 */
public class QuartzStart {

	private static Logger logger = Logger.getLogger(QuartzStart.class);

	public static void main(String[] args) throws Exception {
		// Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// SchedulerFactory factory = new StdSchedulerFactory();
		// Scheduler scheduler = factory.getScheduler();
		// JobDetail jobDetail = newJob(Quartz.class).withIdentity("name",
		// "group").build();
		// Trigger trigger = newTrigger().withIdentity("trigger",
		// "group").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(2)).build();
		// scheduler.scheduleJob(jobDetail, trigger);
		// scheduler.start();

		startSpring();
	}

	private static void startSpring() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext.xml");

	}

	private static void startScheduler() {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = null;
		try {
			sched = sf.getScheduler();
		} catch (Exception ex) {
			logger.error("获取调度程序失败", ex);
			return;
		}

		logger.info("完成任务调度初始化");
		logger.info("开始启动任务调度");

		try {
			sched.start();
		} catch (Exception ex) {
			logger.error("启动任务调度失败", ex);
			return;
		}
		logger.info("任务调度已正常启动");
		while (true) {
			SchedulerMetaData metaData;
			try {
				metaData = sched.getMetaData();
				logger.info("当前共执行了" + metaData.getNumberOfJobsExecuted()
						+ "个任务,运行状态:" + sched.isStarted());
			} catch (Exception ex) {
				logger.error("获取定时任务统计信息失败", ex);
			}
			try {
				Thread.sleep(10000);
			} catch (Exception ex) {
				logger.error("将主线程进入睡眠状态时失败", ex);
			}
		}
	}
}
