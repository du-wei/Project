package com.webapp.utils.code;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.webapp.template.utils.ClassBuilder;
import com.webapp.template.utils.MybatisBuilder;
import com.webapp.utils.model.StudentType;

public class CodeUtilsTest {

	private static final String jdbcCfg = "build_dev.properties";

	@Test
	public void viewProp() {
		MybatisBuilder.viewProp(jdbcCfg);
    }

	@Test
	public void buildModel() {
//		MybatisBuilder.buildByTable("build_dev.properties", Arrays.asList("aic_data"));
	}

	@Test
	public void buildTable() {
//		ClassBuilder.of(Student.class).snake().done(TableBuilder.build(jdbcCfg));
		ClassBuilder.of(StudentType.class).snake()
			.notNull("address")
			.width("address", 260)
			.defVal("address", "111").view();
	}
	@Test
	public void buildOrg() {

		SimpleDataSource ds = new SimpleDataSource();
		try {
			ds.setDriverClassName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl("jdbc:mysql://10.20.69.225:3306/ictest?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
		ds.setUsername("root");
		ds.setPassword("1q2w3e4r");
		Dao dao = new NutDao(ds);
		dao.create(StudentType.class, true);
	}

}
