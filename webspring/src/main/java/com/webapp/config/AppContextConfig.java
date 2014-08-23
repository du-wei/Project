package com.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @Configuration spring注解配置
 * @ComponentScan 扫描spring注解
 * @Import 导入其他的配置类
 * @PropertySource 导入spring静态文件
 */
@Configuration
@ComponentScan("com.webapp")
//@Import({})	
//@PropertySource("")
public class AppContextConfig {
	
}
