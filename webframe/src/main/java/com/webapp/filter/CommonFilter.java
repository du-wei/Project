package com.webapp.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.utils.servlet.CookieUtils;

@WebFilter
public class CommonFilter implements HandlerInterceptor  {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object handler) throws Exception {
//		System.out.println(req.getServletPath() + " -> preHandle");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object handler, ModelAndView modelAndView) throws Exception {
		if(!CookieUtils.hasCookie(req, "token") && !req.getServletPath().matches("js|css|png|jpg|gif")){
			Cookie cookie = new Cookie("token", DigestUtils.sha256Hex("token"));
			resp.addCookie(cookie);
		}
//		System.out.println(req.getServletPath() + " -> postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object handler, Exception ex)
			throws Exception {
//		System.out.println(req.getServletPath() + " -> afterCompletion");
	}
	
}
