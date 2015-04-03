package com.webapp.mvc;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.webapp.constant.Mapping;

@Controller
@RequestMapping(Mapping.MAPPING)
public class ReqMappingController {
	
//	方法注解和参数
//	@ModelAttribute 将参数放到模型中
//	1、@RequestParam绑定单个请求参数值；
//	2、@PathVariable绑定URI模板变量值；
//	3、@CookieValue绑定Cookie数据值
//	4、@RequestHeader绑定请求头数据；
//	5、@ModelValue绑定参数到命令对象；
//	6、@SessionAttributes绑定命令对象到session；放到类的上面
//	7、@RequestBody绑定请求的内容区数据并能进行自动类型转换等。
//	8、@RequestPart绑定“multipart/data”数据，除了能绑定@RequestParam能做到的请求参数外，还能绑定上传的文件等。

//  servlet原生类型
//	HttpServletRequest
//	HttpServletResponse
//	HttpSession
//	Principal
//	Locale
//	InputStream
//	OutputStream
//	Reader
//	Writer

//	WebRequest
//	NativeWebRequest
//	SessionStatus

//	模型数据
//	ModelAndView
//	Map->ModelMap和Model
//	@SessionAttributes
//	@ModelAttribute
	
	
	// params 1 "value" 2 "!value" 3 "param!=value" 4 {"param=value", "param2"}
	@RequestMapping(value = "/map1", method = RequestMethod.POST, params = "userId", headers = "content-type=text/*")
	public String execute1() {
		return "index";
	}
	
	@RequestMapping("/map2")
	public String execute2(
			@RequestParam(value = "para", required = true) int para,
			@CookieValue(value = "JESSIONID", required = false) String sessionId,
			@RequestHeader("Accept-Language") String acceptLanguage) {
		return "index";
	}
	
	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("user", "name");
		form.add("id", "id");
		restTemplate.postForLocation(
				"http://localhost:8080/webspring/para/map6", form);
	}
	
}
