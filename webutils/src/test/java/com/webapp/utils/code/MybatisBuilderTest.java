package com.webapp.utils.code;

import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.webapp.utils.builder.MybatisBuilder;
import com.webapp.utils.builder.TableBuilder;
import com.webapp.utils.model.Student;
import com.webapp.utils.model.StudentType;

public class MybatisBuilderTest {

	private static final String jdbcCfg = "build_dev.properties";

	@Test
	public void viewProp() {
		MybatisBuilder.viewProp(jdbcCfg);
    }

	@Test
	public void buildModel() {
		MybatisBuilder.buildByTable(jdbcCfg, Arrays.asList("linux"));
	}

	@Test
	public void buildTable() {
		TableBuilder.of(Student.class).snake().done(TableBuilder.build(jdbcCfg));
		TableBuilder.of(StudentType.class).snake()
			.notNull("address")
			.width("address", 260)
			.defVal("address", "111").view();
	}
	@Test
	public void buildOrg() {

		Properties jdbc = new Properties();
		SimpleDataSource ds = new SimpleDataSource();
		try {
			ds.setDriverClassName(jdbc.getProperty("driver"));
			jdbc.load(MybatisBuilder.class.getResourceAsStream("/" + jdbcCfg));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl(jdbc.getProperty("jdbc"));
		ds.setUsername(jdbc.getProperty("username"));
		ds.setPassword(jdbc.getProperty("password"));
		Dao dao = new NutDao(ds);
		dao.create(StudentType.class, true);
	}

}
