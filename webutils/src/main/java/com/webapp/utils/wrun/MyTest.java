package com.webapp.utils.wrun;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.webapp.utils.spring.CtxBeanUtils;
import com.webapp.utils.spring.CtxPropsUtils;

public class MyTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

    }

    @Test
	public void testName() throws Exception {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");

//    	SpPropsUtils.viewProp();
    	CtxBeanUtils.viewBean();

//		ApplicationContext ctx = null;
//		ConfigurableApplicationContext cactx = (ConfigurableApplicationContext) ctx;
//		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)cactx.getBeanFactory();
//
//		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Student.class);
//		builder.addPropertyReference("ok", "ok");
//
//		// 注册
//		beanFactory.registerBeanDefinition("beans", builder.getRawBeanDefinition());
	}

}
