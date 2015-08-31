package com.webapp.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("exception", ex);
		return mav;
    }
	
	/* 次异常处理适合用于公共接口 */
//	@ResponseBody
//	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//	public JSONObject handleMethodNotSupportedException(HttpServletRequest req, Exception ex) {
//		JSONObject result = new JSONObject();
////		result.put("code", req.)
//		result.put("msg", "该请求不支持" + req.getMethod());
//		
//		return result;
//    }
	
}
