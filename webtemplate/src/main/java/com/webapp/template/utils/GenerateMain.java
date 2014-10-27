package com.webapp.template.utils;

import generator.template.rapid.App;

import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

import com.webapp.mybatis.Student;

import cn.org.rapid_framework.generator.Generator.GeneratorModel;
import cn.org.rapid_framework.generator.GeneratorControl;
import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorFacade.GeneratorModelUtils;
import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;
import cn.org.rapid_framework.generator.provider.db.table.TableFactory;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;
import cn.org.rapid_framework.generator.provider.java.model.JavaClass;
import cn.org.rapid_framework.generator.util.DBHelper;
import cn.org.rapid_framework.generator.util.DateHelper;
import cn.org.rapid_framework.generator.util.FreemarkerHelper;
import cn.org.rapid_framework.util.EclipseClasspath;

public class GenerateMain {

	@Test
	public void testName() throws Exception {
		Properties props = GeneratorProperties.getProperties();
		Enumeration<Object> keys = props.keys();
		while(keys.hasMoreElements()){
			Object nextElement = keys.nextElement();
//			System.out.println(nextElement + " -> " + props.getProperty(nextElement.toString()));
		}
		System.out.println();

//GeneratorControl control = new GeneratorControl();
//GeneratorModel model = new GeneratorModel();
//GeneratorModelUtils.

		GeneratorFacade g = new GeneratorFacade();
		g.getGenerator().addTemplateRootDir("template\\mytest");
		g.deleteOutRootDir();
		g.generateByAllTable();

	}

//	@Test
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
