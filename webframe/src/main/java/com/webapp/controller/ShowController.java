package com.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.tools.ShowApiUtils;

@Controller
@RequestMapping("/show")
public class ShowController {
	
	//view
	private static final String V = "";
	//query
	private static final String Q = "/q";

	private static final String VIEW_PREFIX = "/show";
	
	@RequestMapping("/tabs")
	public ModelAndView tabs(ModelAndView mav){
		mav.setViewName(VIEW_PREFIX + "/tabs");
		return mav;
	}
	
	@RequestMapping("/date")
	public ModelAndView date(ModelAndView mav){
		mav.setViewName(VIEW_PREFIX + "/date");
		return mav;
	}
	
	@RequestMapping("/input")
	public ModelAndView pdf(ModelAndView mav){
		mav.setViewName(VIEW_PREFIX + "/index_input");
		return mav;
	}
	
	@RequestMapping(V + "/history")
	public ModelAndView history(ModelAndView mav){
		mav.setViewName(VIEW_PREFIX + "/history");
		mav.addObject("history", ShowApiUtils.queryHistory());
		return mav;
	}
	
	@RequestMapping(V + "/tgirl")
	public ModelAndView tgirl(ModelAndView mav, HttpServletRequest req){
		mav.setViewName(VIEW_PREFIX + "/tgirl");
		String page = req.getParameter("page");
		if(StringUtils.isEmpty(page) || page.equals("0")) page = "1";
		JSONObject result = ShowApiUtils.queryTGirl(page);
		mav.addObject("list", result);
		
		int intpage = Integer.parseInt(page);
		mav.addObject("page", intpage);
		mav.addObject("prev", intpage <= 1 ? intpage : intpage-1);
		mav.addObject("next", intpage >= 1 ? intpage+1 : intpage);
		return mav;
	}
	
	@RequestMapping(V + "/girl/list")
	public ModelAndView tgirl_list(ModelAndView mav, HttpServletRequest req){
		mav.setViewName(VIEW_PREFIX + "/tgirl_list");
		String userId = req.getParameter("id");
		String page = req.getParameter("page");
		JSONObject result = ShowApiUtils.queryTGirl(page);
		JSONArray list = result.getJSONArray("contentlist");
		System.out.println(list);
		for(int i=0; i<list.size(); i++){
			JSONObject item = list.getJSONObject(i);
			String id = item.getString("userId");
			if(userId.equals(id)){
				mav.addObject("list", item);
				return mav;
			}
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(Q + "/tgirl")
	public String queryTGirl(HttpServletRequest req){
		String page = req.getParameter("page");
		JSONObject result = ShowApiUtils.queryTGirl(page);
		
		System.out.println(JSON.toJSONString(result, true));
		return result.toJSONString();
	}
	
}
