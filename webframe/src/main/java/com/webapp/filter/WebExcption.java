package com.webapp.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//@Repository
public class WebExcption implements HandlerExceptionResolver {

	@Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mav = new ModelAndView("/404");
		mav.addObject("exception", ex);
		System.out.println("xxxx");
		return mav;
    }

}
