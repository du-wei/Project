package com.webapp.template.utils;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import cn.org.rapid_framework.generator.GeneratorControl;
import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class CodeUtils {

	public enum TemplatType {
		simple("classpath:/template/simple"),
		View("classpath:/template/view");
		TemplatType(String template){
			this.template = template;
		}
		private String template;
		public String getTemplate() {
			return template;
		}

	}
	
	public static void viewProp(String jdbcCfg) {
		setCfg(jdbcCfg);
		Properties props = GeneratorProperties.getProperties();
		
		TreeMap<String, String> map = new TreeMap<String, String>();
		Enumeration<Object> keys = props.keys();
		while(keys.hasMoreElements()){
			Object next = keys.nextElement();
			map.put(next.toString(), props.getProperty(next.toString()));
		}
		
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			System.out.println(next + " --> " + map.get(next));
		}
		
	}
	
	
	public static void buildByTable(String jdbcCfg, String table) {
		buildByTable(jdbcCfg, null, table);
    }
	
	public static void buildByTable(String jdbcCfg, List<String> tables) {
		buildByTable(jdbcCfg, null, tables);
    }
	
	public static void buildByTable(String jdbcCfg, String basePkg, List<String> tables) {
		setCfg(jdbcCfg);
		if(basePkg != null) GeneratorProperties.setProperty("basepackage", basePkg);
		GeneratorControl gc = new GeneratorControl();
		gc.setOverride(true);

		GeneratorFacade gf = new GeneratorFacade();
		gf.getGenerator().addTemplateRootDir(TemplatType.simple.getTemplate());
		
		try {
			gf.deleteOutRootDir();
			gf.generateByTable(tables.toArray(new String[]{}));
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
	public static void buildByTable(String jdbcCfg, String basePkg, String table) {
		setCfg(jdbcCfg);
		if(basePkg != null) GeneratorProperties.setProperty("basepackage", basePkg);
		GeneratorControl gc = new GeneratorControl();
		gc.setOverride(true);

		GeneratorFacade gf = new GeneratorFacade();
		gf.getGenerator().addTemplateRootDir(TemplatType.simple.getTemplate());
		
		try {
			gf.deleteOutRootDir();
			gf.generateByTable(table);
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
	public static void buildAll(String jdbcCfg) {
		buildAll(jdbcCfg, null);
    }
	
	public static void buildAll(String jdbcCfg, String basePkg) {
		setCfg(jdbcCfg);
		if(basePkg != null) GeneratorProperties.setProperty("basepackage", basePkg);
		GeneratorControl gc = new GeneratorControl();
		gc.setOverride(true);

		GeneratorFacade gf = new GeneratorFacade();
		gf.getGenerator().addTemplateRootDir(CodeUtils.class.getResource(TemplatType.simple.getTemplate()).toString());
		try {
			gf.deleteOutRootDir();
			gf.generateByAllTable();
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
	private static void setCfg(String jdbcCfg){
		GeneratorProperties.setProperty("outRoot", System.getProperty("user.dir") + "/codes/");
		String path = CodeUtils.class.getResource("/").getPath();
		
		Properties jdbc = new Properties();
		try {
	        jdbc.load(new FileInputStream(path + "/" + jdbcCfg));
        } catch (Exception e) {
	        e.printStackTrace();
        }
		
		if(jdbc.containsKey("jdbc_url")){
			GeneratorProperties.setProperty("jdbc_url", jdbc.getProperty("jdbc_url"));
		}else if (jdbc.containsKey("url")) {
			GeneratorProperties.setProperty("jdbc_url", jdbc.getProperty("url"));
		}else {
			System.err.println("Must contain jdbc_url or url configuration");
		}
		
		if(jdbc.containsKey("jdbc_driver")){
			GeneratorProperties.setProperty("jdbc_driver", jdbc.getProperty("jdbc_driver"));
		}else if (jdbc.containsKey("driver")) {
			GeneratorProperties.setProperty("jdbc_driver", jdbc.getProperty("driver"));
		}else {
			System.err.println("Must contain jdbc_driver or driver configuration");
		}
		
		if(jdbc.containsKey("jdbc_username")){
			GeneratorProperties.setProperty("jdbc_username", jdbc.getProperty("jdbc_username"));
		}else if (jdbc.containsKey("username")) {
			GeneratorProperties.setProperty("jdbc_username", jdbc.getProperty("username"));
		}else {
			System.err.println("Must contain jdbc_username or username configuration");
		}
		if(jdbc.containsKey("jdbc_password")){
			GeneratorProperties.setProperty("jdbc_password", jdbc.getProperty("jdbc_password"));
		}else if (jdbc.containsKey("password")) {
			GeneratorProperties.setProperty("jdbc_password", jdbc.getProperty("password"));
		}else {
			System.err.println("Must contain jdbc_password or password configuration");
		}
	}

}
