package com.webapp.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.rjeschke.txtmark.Processor;

@Controller
public class MarkDownController {

	@RequestMapping(value={"markdown", "md", "mdwon"})
	public ModelAndView index(ModelAndView mav){
		mav.setViewName("redirect:/markdown/index.html");
		return mav;
	}

	@RequestMapping("/mark/{type}")
	public ModelAndView admin(ModelAndView mav, @PathVariable("type")String type){
		try {
			Path path = Paths.get(MarkDownController.class.getResource("/markdown/" + type + ".md").toURI());
			byte[] readAllBytes = Files.readAllBytes(path);
			String process = Processor.process(new String(readAllBytes));
			 mav.addObject("markdown", process);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("/markdown/index");
		return mav;
	}

}
