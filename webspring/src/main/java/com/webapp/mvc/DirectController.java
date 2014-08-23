package com.webapp.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/direct")
public class DirectController {

	@RequestMapping("/redirect1")
	public RedirectView redirect() {
		RedirectView view = new RedirectView("redirectResult");
		view.addStaticAttribute("name", "king");
		return view; // 重定向到相对路径
	}

	@RequestMapping("/str1")
	public String str1(RedirectAttributes para) {
		para.addFlashAttribute("msg", "name"); // 重定向的参数不会显示在地址栏中
		para.addAttribute("name", "king"); // 重定向的参数显示在地址栏中

		return "redirect:redirectResult"; // String重定向到相对路径
	}

	@RequestMapping("/str2")
	public String str2() {
		return "redirect:/rest/index"; // String重定向到绝对路径
	}

	@RequestMapping("/view1")
	public ModelAndView view1() { // ModelAndView重定向到相对路径
		ModelAndView view = new ModelAndView("redirect:redirectResult");
		view.addObject("name", "king");
		return view;
	}

	@RequestMapping("/view2")
	public ModelAndView view2() { // ModelAndView重定向到绝对路径
		ModelAndView view = new ModelAndView("redirect:/rest/index");
		view.addObject("name", "king");
		return view;
	}

	@RequestMapping("/redirectResult")
	public String getRedirect(HttpServletRequest req) {
		// 只能用此方法取flash属性值，并且只能一次，刷新无效
		Map<String, ?> para = RequestContextUtils.getInputFlashMap(req);
		if (para != null)
			System.out.println(para.get("msg"));

		System.out.println(req.getParameter("name"));// 取重定向的参数显示在地址栏中值
		return "index";
	}

}
