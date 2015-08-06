package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value={"/", "/index"})
	public ModelAndView admin(ModelAndView mav){
		mav.setViewName("/index");
		return mav;
	}
	@RequestMapping("/indexs")
	public ModelAndView indexs(ModelAndView mav){
		mav.setViewName("/indexs");
		return mav;
	}
	
}
