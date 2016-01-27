package com.webapp.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sh")
public class LinuxShHelp {

	private static final String BASE = "/sh/";

	@ResponseBody
	@RequestMapping("/{type}")
	public String admin(@PathVariable("type") String type){
		try {
			return IOUtils.toString(this.getClass().getResourceAsStream(BASE + type));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@ResponseBody
	@RequestMapping("/list")
	public String list(){
		StringBuffer result = new StringBuffer();
		try {
			Path path = Paths.get(this.getClass().getResource(BASE).toURI());
			Files.list(path).forEach(x->result.append(x.getFileName()+"\n"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
