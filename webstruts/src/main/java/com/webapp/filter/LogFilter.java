/**   
 * @Title: LogFilter.java 
 * @Package com.webapp.filter 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-25 下午6:12:45 
 * @version V1.0   
 */
package com.webapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * @ClassName: LogFilter.java
 * @Package com.webapp.filter
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-25 下午6:12:45
 * @version V1.0
 */
@WebFilter(filterName = "lgoFilter", urlPatterns = "/*")
public class LogFilter extends StrutsPrepareAndExecuteFilter {

	private Logger logger = Logger.getLogger(LogFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		logger.warn("doFilter start...");
		super.doFilter(req, res, chain);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
