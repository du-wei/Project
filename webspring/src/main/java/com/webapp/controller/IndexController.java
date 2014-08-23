/**   
 * @Title: IndexController.java 
 * @Package com.webapp.controller 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-17 下午6:09:09 
 * @version V1.0   
 */
package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController.java
 * @Package com.webapp.controller
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-17 下午6:09:09
 * @version V1.0
 */

@Controller
public class IndexController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

}
