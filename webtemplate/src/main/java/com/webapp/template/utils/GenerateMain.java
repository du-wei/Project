package com.webapp.template.utils;

import java.util.Properties;

import org.junit.Test;

import com.webapp.mybatis.Student;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class GenerateMain {

	@Test
	public void testName() throws Exception {
		Properties props = GeneratorProperties.getProperties();


		GeneratorFacade g = new GeneratorFacade();
		g.getGenerator().addTemplateRootDir("template\\mytest");

		g.deleteOutRootDir();
		g.generateByClass(Student.class);
	}

	@Test
	public void main() throws Exception {
		GeneratorFacade g = new GeneratorFacade();
		g.getGenerator().addTemplateRootDir("template\\mybatis");

		// g.setGenerator(generator);
		GeneratorFacade.printAllTableNames();
		g.deleteOutRootDir();
		// 删除生成器的输出目录//
		// g.generateByTable("USER","template");
		// 通过数据库表生成文件,template为模板的根目录
		g.generateByAllTable();
		// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
		// g.generateByClass(Blog.class,"template_clazz");
		// g.deleteByTable("table_name", "template");
		// 删除生成的文件
	}

}
