package com.webapp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class WebExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView mav = new ModelAndView("/error/404");
		mav.addObject("exception", ex);
		
		logger.error("", ex);
		return mav;
    }
	
	/* 次异常处理适合用于公共接口 */
//	@ResponseBody
//	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//	public JSONObject handleMethodNotSupportedException(HttpServletRequest req, Exception ex) {
//		JSONObject result = new JSONObject();
//		result.put("msg", "该请求不支持" + req.getMethod());
//		
//		return result;
//    }
	
}
