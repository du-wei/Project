/**
 * @Title: SpringJob.java
 * @Package com.webapp.quartz.spring
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-1 下午4:08:59
 * @version V1.0
 */
package com.webapp.quartz.spring;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: SpringJob.java
 * @Package com.webapp.quartz.spring
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-1 下午4:08:59
 * @version V1.0
 */
public class SpringJob {

	private static Logger logger = LogManager.getLogger(SpringJob.class);

	public void execute() {

		logger.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss") + " spring job....");
	}

}
