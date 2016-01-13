package com.webapp.template.utils;

import java.util.Properties;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableBuilder<T> {

	private static final Logger logger = LoggerFactory.getLogger(TableBuilder.class);
	private SimpleDataSource ds;
	public TableBuilder(String jdbcCfg) {

		Properties jdbc = new Properties();
		try {
	        jdbc.load(MybatisBuilder.class.getResourceAsStream("/" + jdbcCfg));
        } catch (Exception e) {
        	logger.error("read jdbcCfg error ", e);
        }

        ds = new SimpleDataSource();
		try {
			ds.setDriverClassName(jdbc.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			logger.error("load jdbc driver error ", e);
		}
		ds.setJdbcUrl(jdbc.getProperty("url"));
		ds.setUsername(jdbc.getProperty("username"));
		ds.setPassword(jdbc.getProperty("password"));
	}

	public static <T> TableBuilder<T> build(String jdbcCfg) {
		return new TableBuilder<>(jdbcCfg);
	}

	public void createTable(String clzStr, String pkg, String name) {
		Class<?> compile = ClassCompiler.compile(pkg + "." + name ,clzStr);
		Dao dao = new NutDao(ds);
		dao.create(compile, true);
	}

}
