package com.webapp.config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringConfigTest {
	
	@Test
	public void testConfig(){
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringGlobalConfig.class);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml", "spring-mvc.xml");
		
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppContextConfig.class);
		
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		
		
//		//2、springmvc上下文  
//        AnnotationConfigWebApplicationContext springMvcContext = new AnnotationConfigWebApplicationContext();  
//        springMvcContext.register(MvcConfig.class);  
//        //3、DispatcherServlet  
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(springMvcContext);  
//        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);  
//        dynamic.setLoadOnStartup(1);  
//        dynamic.addMapping("/");  
//  
//        //4、CharacterEncodingFilter  
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
//        characterEncodingFilter.setEncoding("utf-8");  
//        FilterRegistration filterRegistration =  
//                sc.addFilter("characterEncodingFilter", characterEncodingFilter);  
//        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
//		
	}
	
}
