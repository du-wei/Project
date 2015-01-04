package com.webapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DataBinderAndMapping {
	
	
	@RequestMapping("testFile")
	public String testFile(MultipartFile file, String desc){
		System.out.println(desc);
		System.out.println(file.getOriginalFilename());
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("databind")
	public String dataBind(String name){
		System.out.println(name);
		return "databind";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//数据类型转换，格式化， 数据校验，处理方法签名等
	    System.out.println("databind -> " + binder.getObjectName());
    }
	
}
