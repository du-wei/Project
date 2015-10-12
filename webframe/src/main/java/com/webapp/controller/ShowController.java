package com.webapp.controller;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.tools.JodaUtils;
import com.webapp.tools.ShowApiUtils;

@Controller
@RequestMapping("/show")
public class ShowController {
	public static void main(String[] args) {
	    System.out.println(System.getProperties().getProperty("os.name"));
    }
	//view
	private static final String V = "";
	//query
	private static final String Q = "/q";

	private static final String VIEW_PREFIX = "/show";
	
	@RequestMapping("/{type}")
	public ModelAndView tabs(ModelAndView mav, @PathVariable("type")String type){
		/*
		 * velocity
		 * tabs  
		 * date 
		 * death
		 * input
		 */
		
		mav.setViewName(VIEW_PREFIX + "/" + type);
		return mav;
	}
	
	public static String shortPath(Path path, String base) {
	    return path.toString().replace(base, "").replace("\\", "/");
    }
	
	@RequestMapping("/study")
	public ModelAndView study(ModelAndView mav, HttpServletRequest req){
		mav.setViewName(VIEW_PREFIX + "/study");
		
		String base = "/data/pdf/";
		if(System.getProperties().getProperty("os.name").startsWith("Win")){
			base = "V:\\360cloud\\GoogleAdmin\\";
		}
		
		String path_s = req.getParameter("path");
		if(StringUtils.isEmpty(path_s)) path_s = "";
		
		Path path_p = Paths.get(base + path_s);
		
		JSONArray result = new JSONArray();
	    DirectoryStream<Path> stream = null;
        try {
	        stream = Files.newDirectoryStream(path_p);
        } catch (IOException e) {
	        e.printStackTrace();
        }
	    for(Path path : stream){
	    	JSONObject item = new JSONObject();
	    	item.put("key", path.getFileName().toString());
	    	item.put("val", shortPath(path, base));
	    	result.add(item);
	    }
	    mav.addObject("paths", result);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(Q + "/death")
	public String death(HttpServletRequest req){
		String date = req.getParameter("date");
		Integer age = Integer.parseInt(req.getParameter("age"));
		DateTime parse = DateTime.parse(date);
		
		JSONObject death = new JSONObject();
		if(!parse.isBeforeNow()){
			death.put("msg", "您可能还没有出生吧!!!");
			return death.toJSONString();
		}
		if(parse.plusYears(age).isBeforeNow()){
			death.put("msg", "您好像已经死了!!!");
			return death.toJSONString();
		}
		death = JodaUtils.death(parse.toDate(), age);
		return death.toJSONString();
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
