/**
 * @Title: Quartz.java
 * @Package com.webapp.scheduled
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-27 下午2:47:41
 * @version V1.0
 */
package com.webapp.quartz.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName: Quartz.java
 * @Package com.webapp.scheduled
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-27 下午2:47:41
 * @version V1.0
 */
public class Quartz implements Job {

	private static Logger logger = LogManager.getLogger(Quartz.class);

	/*
	 * (non-Javadoc) <p>Title: execute</p> <p>Description: </p>
	 *
	 * @param arg0
	 *
	 * @throws JobExecutionException
	 *
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("start...");
	}

}
