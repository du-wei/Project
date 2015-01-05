package com.webapp.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AppModelAttr {
	
	//所有方法之前执行，并将结果放到模型中
		@ModelAttribute("model")
		public String befor() {
			return "modelAttr";
		}
	
}
