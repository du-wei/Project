package com.webapp.base;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.webapp.config.MvcConfig;

public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext  dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(MvcConfig.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(".jsp");
		System.out.println("web...");
    }

}
