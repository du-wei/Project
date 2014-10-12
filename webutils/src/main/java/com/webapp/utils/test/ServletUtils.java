package com.webapp.utils.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class ServletUtils {

	/**
	 * @param req
	 * @return If it is a get request, return true
	 */
	public static boolean isGet(HttpServletRequest req) {
		return req.getMethod().equalsIgnoreCase("get");
	}

	/**
	 * @param req
	 * @return If it is a post request, return true
	 */
	public static boolean isPost(HttpServletRequest req) {
		return req.getMethod().equalsIgnoreCase("post");
	}

	/**
	 * @param req
	 * @return From which pages link to come over
	 */
	public static String getReferer(HttpServletRequest req) {
		return req.getHeader("Referer");
	}

	/**
	 * For example
     * <pre>http://www.mydomain.com/request -> www.mydomain.com</pre>
	 * @param req
	 * @return The requested domain name
	 */
	public static String getDomain(HttpServletRequest req) {
		return req.getServerName();
	}

	/**
	 * For example
     * <pre>http://www.mydomain.com/request -> http://www.mydomain.com</pre>
	 * @param req
	 * @return The requested base url
	 */
	public static String getBaseUrl(HttpServletRequest req) {
		return "http://" + req.getServerName();
	}

	/**
	 * For example
     * <pre>http://localhost:8080/request -> 8080</pre>
	 * @param req
	 * @return The requested port
	 */
	public static int getPort(HttpServletRequest req) {
		return req.getServerPort();
	}

	/**
	 * For example
     * <pre>http://www.mydomain.com/request -> ip</pre>
	 * @param req
	 * @return The requested ip
	 */
	public static String getIpAddr(HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip)) ip = req.getHeader("Proxy-Client-IP");
        if (StringUtils.isEmpty(ip)) ip = req.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isEmpty(ip)) ip = req.getRemoteAddr();
        return ip;
    }

}
