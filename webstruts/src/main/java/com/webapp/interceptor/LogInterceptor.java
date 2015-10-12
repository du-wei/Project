/**
 * @Title: LogInterceptor.java
 * @Package com.webapp.interceptor
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-25 下午5:55:11
 * @version V1.0
 */
package com.webapp.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @ClassName: LogInterceptor.java
 * @Package com.webapp.interceptor
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-25 下午5:55:11
 * @version V1.0
 */
public class LogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(LogInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.warn("LogInterceptor start....");
		return invocation.invoke();
	}

}
