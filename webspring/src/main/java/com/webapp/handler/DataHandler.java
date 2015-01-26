package com.webapp.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DataHandler {

	@InitBinder
	// ("name")
	public void initBinder(WebDataBinder binder, WebRequest req) {
		// 数据类型转换，格式化， 数据校验，处理方法签名等
		System.out.println("databind -> " + binder.getObjectName());

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
		        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false));
	}

	// 所有方法之前执行，并将结果放到模型中
	@ModelAttribute("model")
	public String befor() {
		return "modelAttr";
	}

}
