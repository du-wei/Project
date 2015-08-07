package com.webapp.template.utils;

import java.util.Enumeration;
import java.util.Properties;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class GenerateMain {

	public enum TmpEnum {
		model_simple("template\\model_simple");
		TmpEnum(String template){
			this.template = template;
		}
		private String template;
		public String getTemplate() {
			return template;
		}

	}

	public static void createModel(String table, String basePkg, TmpEnum tmp) {
		GeneratorProperties.setProperty("basepackage", basePkg);
//		GeneratorControl gc = new GeneratorControl();
//		gc.setOverride(true);

		GeneratorFacade gf = new GeneratorFacade();
		gf.getGenerator().addTemplateRootDir(tmp.getTemplate());
		try {
	        gf.generateByTable(table);
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }

	public static void viewProp() {
		Properties props = GeneratorProperties.getProperties();
		Enumeration<Object> keys = props.keys();
		while(keys.hasMoreElements()){
			Object nextElement = keys.nextElement();
			System.out.println(nextElement + " -> " + props.getProperty(nextElement.toString()));
		}
	}

}
