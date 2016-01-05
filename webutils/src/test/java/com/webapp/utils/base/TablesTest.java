package com.webapp.utils.base;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.Daos;

import com.webapp.utils.model.Student;

public class TablesTest {

	public static void main(String[] args) {
		SimpleDataSource ds = new SimpleDataSource();
		try {
			ds.setDriverClassName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //默认加载了大部分数据库的驱动!!
		ds.setJdbcUrl("jdbc:mysql://10.20.69.225:3306/chunfeng_test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
		ds.setUsername("root");
		ds.setPassword("1q2w3e4r");


		Dao dao = new NutDao(ds);
		Daos.createTablesInPackage(dao, Student.class, true);

	}

}
