package com.webapp.quartz.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartzUtils {

	static SchedulerFactoryBean schedulerFactoryBean = null;
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	public static void startJob() {
	}

	/**
	 * 获取所有的Job
	 */
	public static void getAllJob() {
	}

	/**
	 * 获取正在运行的job
	 */
	public static void isRunJob() {
//	    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
//	    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
//	    for (JobExecutionContext executingJob : executingJobs) {
//	    ScheduleJob job = new ScheduleJob();
//	    JobDetail jobDetail = executingJob.getJobDetail();
//	    JobKey jobKey = jobDetail.getKey();
//	    Trigger trigger = executingJob.getTrigger();
//	    job.setJobName(jobKey.getName());
//	    job.setJobGroup(jobKey.getGroup());
//	    job.setDesc("触发器:" + trigger.getKey());
//	    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//	    job.setJobStatus(triggerState.name());
//	    if (trigger instanceof CronTrigger) {
//	    CronTrigger cronTrigger = (CronTrigger) trigger;
//	    String cronExpression = cronTrigger.getCronExpression();
//	    job.setCronExpression(cronExpression);
//	    }
//	    jobList.add(job);
//	    }


	}

	/**
	 * 暂停Job
	 */
	public static void pauseJob(){
//	    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//	    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
//	    scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复Job
	 */
	public static void resumeJob(){
//	    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//	    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
//	    scheduler.resumeJob(jobKey);

	}

	/**
	 * 立即运行任务一次
	 */
	public static void triggerJob(){
//	    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//	    JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
//	    scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新任务表达式
	 */
	public static void rescheduleJob(){


//	    Scheduler scheduler = schedulerFactoryBean.getScheduler();
//
//	    TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),
//	    scheduleJob.getJobGroup());
//
//	    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
//	    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//	    //表达式调度构建器
//	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob
//	    .getCronExpression());
//
//	    //按新的cronExpression表达式重新构建trigger
//	    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
//	    .withSchedule(scheduleBuilder).build();
//
//	    //按新的trigger重新设置job执行
//	    scheduler.rescheduleJob(triggerKey, trigger);


	}
}
