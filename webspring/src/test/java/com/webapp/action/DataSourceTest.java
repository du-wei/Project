package com.webapp.action;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import com.webapp.base.RunnerWeb;
import com.webapp.utils.datasource.MultiDataSource;

public class DataSourceTest extends RunnerWeb {

	@Test
	public void datasource() {

//		@DataSource

//	    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BasicDataSource data = (BasicDataSource)wac.getBean("dataSource");
		System.out.println(data.getUrl());
		BasicDataSource data2 = (BasicDataSource)wac.getBean("dataSource_new");
		System.out.println(data2.getUrl());

		MultiDataSource ds = (MultiDataSource)wac.getBean("multiDataSource");

		try {
			MultiDataSource.setDefDataSource();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Object datasourcekey = MultiDataSource.getDatasourcekey();
		System.out.println(datasourcekey);

    }

}
