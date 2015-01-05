package com.webapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.webapp.constant.Mapping;
import com.webapp.utils.model.Student;

@Controller
@RequestMapping(Mapping.DATA)
public class DataBinderController {
	
	@ResponseBody
	@RequestMapping("body")//不能处理 multipart/form-data 处理application/json, application/xml等
	public String inputPara(@RequestBody Student student) {
		//json->obj主要用于定制请求
		System.out.println(student);
	    return JSON.toJSONString(student);
    }
	
	@ResponseBody
	@RequestMapping("convert")
	public String convert(Student student){
		System.out.println(student);
		return JSON.toJSONString(student);
	}
	
	@RequestMapping("upload")
	public String testFile(MultipartFile file, String desc){
		System.out.println(desc);
		System.out.println(file.getOriginalFilename());
		return "index";
	}
	
}
