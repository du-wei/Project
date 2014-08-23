package com.webapp.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidStatService;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.JdbcUtils;

public class DruidStatServiceTest2 {

	private DruidDataSource dataSource;
	private DruidDataSource dataSource2;

	// every test, two data source initialized.
	@Before
	public void setUp() throws Exception {
		// DruidStatService is singleton, reset all for other testcase.
		DruidStatService.getInstance().service("/reset-all.json");
		// mock datasource1
		dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mock:xxx");
		dataSource.setFilters("stat");
		dataSource.setTestOnBorrow(false);
		dataSource.init();
		// mock datasource2
		dataSource2 = new DruidDataSource();
		dataSource2.setUrl("jdbc:mock:xxx2");
		dataSource2.setFilters("stat");
		dataSource2.setTestOnBorrow(false);
		dataSource2.init();
	}

	@After
	public void tearDown() throws Exception {
		JdbcUtils.close(dataSource);
		JdbcUtils.close(dataSource2);
	}

	@Test
	public void test_statService_getSqlList() throws Exception {
		String sql = "select 1";

		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Thread.sleep(1);
		rs.close();
		stmt.close();
		conn.close();

		// second data source
		String sql2 = "select 1,1";
		conn = dataSource2.getConnection();
		stmt = conn.prepareStatement(sql2);
		rs = stmt.executeQuery();
		rs.next();
		Thread.sleep(1);
		rs.close();
		stmt.close();
		conn.close();

		String result = DruidStatService.getInstance().service("/sql.json");
		Map<String, Object> resultMap = (Map<String, Object>) JSONUtils
				.parse(result);

		List<Map<String, Object>> sqlList = (List<Map<String, Object>>) resultMap
				.get("Content");

		assertThat(sqlList.size(), equalTo(2));
		for (Map<String, Object> sqlStat : sqlList) {
			assertThat((Integer) sqlStat.get("RunningCount"), equalTo(0));
			assertThat((Integer) sqlStat.get("ExecuteCount"), equalTo(1));
			assertThat((Integer) sqlStat.get("FetchRowCount"), equalTo(1));
			assertThat((Integer) sqlStat.get("EffectedRowCount"), equalTo(0));
		}
	}

	@Test
	public void test_statService_getSqlById() throws Exception {
		String sql = "select 1";

		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		rs.close();
		stmt.close();
		conn.close();

		// second data source
		String sql2 = "select 2";
		conn = dataSource2.getConnection();
		stmt = conn.prepareStatement(sql2);
		rs = stmt.executeQuery();
		rs.next();
		rs.close();
		stmt.close();
		conn.close();

		long id = dataSource.getSqlStatMap().values().iterator().next().getId();
		String result = DruidStatService.getInstance().service(
				"/sql-" + id + ".json");
		Map<String, Object> resultMap = (Map<String, Object>) JSONUtils
				.parse(result);
		Map<String, Object> sqlStat = (Map<String, Object>) resultMap
				.get("Content");

		assertThat((Integer) sqlStat.get("RunningCount"), equalTo(0));
		assertThat((Integer) sqlStat.get("ExecuteCount"), equalTo(1));
		assertThat((Integer) sqlStat.get("FetchRowCount"), equalTo(1));
		assertThat((Integer) sqlStat.get("EffectedRowCount"), equalTo(0));
		assertThat((String) sqlStat.get("SQL"), equalTo(sql));

		id = dataSource2.getSqlStatMap().values().iterator().next().getId();
		result = DruidStatService.getInstance().service("/sql-" + id + ".json");
		resultMap = (Map<String, Object>) JSONUtils.parse(result);
		sqlStat = (Map<String, Object>) resultMap.get("Content");

		assertThat((Integer) sqlStat.get("RunningCount"), equalTo(0));
		assertThat((Integer) sqlStat.get("ExecuteCount"), equalTo(1));
		assertThat((Integer) sqlStat.get("FetchRowCount"), equalTo(1));
		assertThat((Integer) sqlStat.get("EffectedRowCount"), equalTo(0));
		assertThat((String) sqlStat.get("SQL"), equalTo(sql2));

		String result2 = DruidStatService.getInstance().service(
				"/sql-" + Integer.MAX_VALUE + ".json");
		resultMap = (Map<String, Object>) JSONUtils.parse(result2);
		assertThat(resultMap.get("Content"), is(nullValue()));
	}

	@Test
	public void test_statService_getDataSourceList() throws Exception {
		String result = DruidStatService.getInstance().service(
				"/datasource.json");
		Map<String, Object> resultMap = (Map<String, Object>) JSONUtils
				.parse(result);
		List<Map<String, Object>> dataSourceList = (List<Map<String, Object>>) resultMap
				.get("Content");

		assertThat(dataSourceList.size(), equalTo(2));

		Map<String, Object> dataSourceStat = dataSourceList.get(0);
		assertThat((Integer) dataSourceStat.get("PoolingCount"), equalTo(0));
		assertThat((Integer) dataSourceStat.get("ActiveCount"), equalTo(0));
	}

}