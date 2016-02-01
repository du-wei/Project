package com.webapp.utils.builder;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.org.rapid_framework.generator.GeneratorControl;
import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public final class MybatisBuilder {

	private static final Logger logger = LoggerFactory.getLogger(MybatisBuilder.class);
	private static final String PASSWORD = "password";
	private static final String USERNAME = "username";
	private static final String DRIVER = "driver";
	private static final String URL = "url";

	public enum TemplatType {
		simple("classpath:/template/simple"),
		view("classpath:/template/view");
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
			logger.info("{} --> {}", next, map.get(next));
		}
	}

	public static void buildByTable(String jdbcCfg, String table) {
		buildByTable(jdbcCfg, null, table);
    }

	public static void buildByTable(String jdbcCfg, List<String> tables) {
		buildByTable(jdbcCfg, null, tables);
    }

	public static void buildByTable(String jdbcCfg, String basePkg, List<String> tables) {
		GeneratorFacade gf = build(jdbcCfg, basePkg);
		try {
			gf.deleteOutRootDir();
			gf.generateByTable(tables.toArray(new String[]{}));
        } catch (Exception e) {
	        logger.info(" error buildByTable ", e);
        }
    }

	public static void buildByTable(String jdbcCfg, String basePkg, String table) {
		GeneratorFacade gf = build(jdbcCfg, basePkg);
		try {
			gf.deleteOutRootDir();
			gf.generateByTable(table);
        } catch (Exception e) {
	        logger.info(" error buildByTable ", e);
        }
    }

	public static void buildAll(String jdbcCfg, String basePkg) {
		GeneratorFacade gf = build(jdbcCfg, basePkg);
		try {
			gf.deleteOutRootDir();
			gf.generateByAllTable();
        } catch (Exception e) {
	        logger.info(" error buildAll ", e);
        }
    }

	public static void buildAll(String jdbcCfg) {
		buildAll(jdbcCfg, null);
    }

	private static GeneratorFacade build(String jdbcCfg, String basePkg) {
		setCfg(jdbcCfg);
		if(basePkg != null) GeneratorProperties.setProperty("basepackage", basePkg);
		GeneratorControl gc = new GeneratorControl();
		gc.setOverride(true);

		GeneratorFacade gf = new GeneratorFacade();
		gf.getGenerator().addTemplateRootDir(TemplatType.simple.getTemplate());
		return gf;
	}

	private static void setCfg(String jdbcCfg){
		GeneratorProperties.setProperty("outRoot", System.getProperty("user.dir") + "/codes/");

		Properties jdbc = new Properties();
		try {
	        jdbc.load(MybatisBuilder.class.getResourceAsStream("/" + jdbcCfg));
        } catch (Exception e) {
	        e.printStackTrace();
        }

		setJdbc(jdbc, URL);
		setJdbc(jdbc, DRIVER);
		setJdbc(jdbc, USERNAME);
		setJdbc(jdbc, PASSWORD);
	}


	private static void setJdbc(Properties jdbc, String key) {
		String jdbc_key = "jdbc_" + key;

		if(jdbc.containsKey(jdbc_key)){
			GeneratorProperties.setProperty(jdbc_key, jdbc.getProperty(jdbc_key));
		}else if (jdbc.containsKey(key)) {
			GeneratorProperties.setProperty(jdbc_key, jdbc.getProperty(key));
		}else {
			logger.error("Must contain {} or {} configuration", jdbc_key, key);
		}
	}

}
