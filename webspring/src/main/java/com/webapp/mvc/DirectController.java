package com.webapp.mvc;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.webapp.constant.Mapping;

@Controller
@RequestMapping(Mapping.BASE)
public class DirectController {
	
	/*
	 * 相对路径 /admin/pub
	 * 绝对路径  pub
	 * forward
	 * redirect
	 * RedirectView
	 */
	
//	方法注解和参数
//	@ModelAttribute 将参数放到模型中 
//	1、@RequestParam绑定单个请求参数值；
//	2、@PathVariable绑定URI模板变量值；
//	3、@CookieValue绑定Cookie数据值
//	4、@RequestHeader绑定请求头数据；
//	5、@ModelValue绑定参数到命令对象；
//	6、@SessionAttributes绑定命令对象到session；
//	7、@RequestBody绑定请求的内容区数据并能进行自动类型转换等。
//	8、@RequestPart绑定“multipart/data”数据，除了能绑定@RequestParam能做到的请求参数外，还能绑定上传的文件等。
	
	
//	HttpServletRequest
//	HttpServletResponse
//	HttpSession
//	WebRequest
//	NativeWebRequest
//	OutputStream
//	ModelMap 访问模型对象
//	Model
//	Map
//	SessionStatus
	
	private String key = "key";
	private String val = "val";
	
	@RequestMapping(Mapping.pub_view)
	public ModelAndView pub_view(ModelAndView mav, HttpServletRequest req){
		mav.setViewName(Mapping.pub_view);
		
		Map<String, ?> para = RequestContextUtils.getInputFlashMap(req);
		if (para != null)
			System.out.println(para.get(key));
		System.out.println(req.getParameter(key));
		System.out.println(req.getAttribute(key));
		return mav;
	}
	
	//转发
	@RequestMapping(Mapping.FORWARD_MAV)
	public ModelAndView forword_mav(){
		ModelAndView view = new ModelAndView("forward:" + Mapping.pub_view);
		return view;
	}
	
	@RequestMapping(Mapping.FORWARD_STR)
	public String forword_str(){
		return "forward:pub_view";
	}
	
	//重定向
	@RequestMapping(Mapping.REDIRECT_MAV)
	public ModelAndView redirect_mav() {
		ModelAndView view = new ModelAndView("redirect:" + Mapping.pub_view);
		view.addObject(key, val);		//地址栏带参数
		return view;
	}
	@RequestMapping(Mapping.REDIRECT_MAV_RV)
	public ModelAndView redirect_mav_rv(HttpServletRequest req, HttpServletResponse resp) {
		RedirectView redirectView = new RedirectView(Mapping.pub_view);
		
		FlashMap outputFlashMap = new FlashMap();
		outputFlashMap.put(key, val);
		
		req.setAttribute(DispatcherServlet.INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(outputFlashMap));
		
		ModelAndView view = new ModelAndView(redirectView);
//		view.setView(new RedirectView(Mapping.pub_view));
		return view; 
	}
	@RequestMapping(Mapping.REDIRECT_RV)
	public RedirectView redirect_rv() {
		RedirectView view = new RedirectView(Mapping.pub_view);
		view.addStaticAttribute(key, val);	//地址栏带参数
		return view; 
	}
	@RequestMapping(Mapping.REDIRECT_STR)
	public String redirect_str() {
//		UriComponentsBuilder.fromPath("").
		return "redirect:" + Mapping.pub_view;
	}
	
	

	@RequestMapping(Mapping.REDIRECT_ALL)
	public String str1(RedirectAttributes para) {
		para.addFlashAttribute(key, val); // 重定向的参数不会显示在地址栏中
		para.addAttribute(key, val); // 重定向的参数显示在地址栏中
		return "redirect:pub_view"; // String重定向到相对路径
	}

}
