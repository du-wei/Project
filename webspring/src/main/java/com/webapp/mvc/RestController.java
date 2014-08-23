/**
 * @Title: TestXmlView.java
 * @Package com.webapp.view
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-6 下午2:56:05
 * @version V1.0
 */
package com.webapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: TestXmlView.java
 * @Package com.webapp.view
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-6 下午2:56:05
 * @version V1.0
 */
@Controller
@RequestMapping("rest")
public class RestController {

	@RequestMapping("index")
	public String execute() {
		return "index";
	}

	@RequestMapping("/index/{name}")
	public ModelAndView get(@PathVariable String name) {
		System.out.println(name);
		return new ModelAndView("index", "msg", name);
	}

	@RequestMapping("/index/{name}/{id}")
	public ModelAndView get(@PathVariable String name, @PathVariable String id) {
		System.out.println(name + '-' + id);
		String msg = name + " - " + id;
		return new ModelAndView("index", "msg", msg);
	}

}
