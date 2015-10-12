package com.webapp.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.constant.Mapping;

@Controller
@RequestMapping(Mapping.VIEW)
public class ViewController {

	
	@RequestMapping(Mapping.pub_view)
	public ModelAndView pub_view(ModelAndView mav, HttpServletRequest req){
		mav.setViewName(Mapping.pub_view);

		String view = req.getParameter("t");
		if(view != null){
			if(view.equals("jsp")){
				mav.setViewName(Mapping.pub_view+".jsp");
			}
			if(view.equals("ftl")){
				mav.setViewName(Mapping.pub_view+".ftl");
			}
			if(view.equals("vm")){
				mav.setViewName(Mapping.pub_view+".vm");
			}
		}
		
		return mav;
	}

}
