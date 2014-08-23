/**   
 * @Title: SpringJob.java 
 * @Package com.webapp.quartz.spring 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-1 下午4:08:59 
 * @version V1.0   
 */
package com.webapp.quartz.spring;

import org.apache.log4j.Logger;

/**
 * @ClassName: SpringJob.java
 * @Package com.webapp.quartz.spring
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-1 下午4:08:59
 * @version V1.0
 */
public class SpringJob {

	private static Logger logger = Logger.getLogger(SpringJob.class);

	public void execute() {
		logger.info("spring job....");
	}

}
