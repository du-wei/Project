package com.webapp.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//@Repository
public class WebExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);
	
	@Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mav = new ModelAndView("/404");
		mav.addObject("exception", ex);
		logger.error("", ex);
		return mav;
    }

}
