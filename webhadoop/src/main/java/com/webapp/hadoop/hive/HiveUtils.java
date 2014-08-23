package com.webapp.hadoop.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class HiveUtils {

	public static void main(String[] args) {
		HiveUtils hiveUtils = new HiveUtils();
		List<String> listTables = hiveUtils.listTables();
		listTables.forEach(t -> System.out.println(t));

	}

	private static Logger logger = Logger.getLogger(HiveUtils.class);
	private static String driverClass = "org.apache.hadoop.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive://192.168.88.10:10000/default";
	private Connection conn = null;

	/**
	 * 获取hive连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName(driverClass);
				conn = DriverManager.getConnection(url, "hive", "hive");
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e.getCause().getMessage(), e);
			}
		}
		return conn;
	}

	/**
	 * 列出当前数据库的所有表
	 * 
	 * @return
	 */
	public List<String> listTables() {
		return listTables(null);
	}

	/**
	 * 列出dataBaseName的所有表
	 * 
	 * @param dataBaseName
	 * @return
	 */
	public List<String> listTables(String dataBaseName) {
		Statement stmt = null;
		ResultSet res = null;
		List<String> tables = new LinkedList<String>();
		try {
			stmt = getConnection().createStatement();
			if (dataBaseName != null && dataBaseName.trim().length() > 0) {
				stmt.executeQuery("use " + dataBaseName);
			}
			res = stmt.executeQuery("show tables");
			while (res.next()) {
				tables.add(res.getString(1));
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			close(res);
			close(stmt);
		}

		for (int i = 0; i < tables.size(); i++) {
			System.out.println(tables.get(i));
		}
		return tables;
	}

	/**
	 * 执行非查询的sql语句，比如创建表，加载数据等等
	 * 
	 * @param sql
	 * @return
	 */
	public boolean execute(String sql) {
		Statement stmt = null;
		boolean rel = true;
		try {
			stmt = getConnection().createStatement();
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			rel = false;
			logger.error(e.getCause().getMessage(), e);
		} finally {
			close(stmt);
		}
		return rel;
	}

	/**
	 * 使用Statement查询数据，返回ResultSet
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet query(String sql) {
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = getConnection().createStatement();
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			close(stmt);
		}
		return res;
	}

	/**
	 * 使用PreparedStatement查询数据，返回ResultSet
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public ResultSet query(String sql, String[] values) {
		PreparedStatement pst = null;
		ResultSet res = null;
		try {
			pst = getConnection().prepareStatement(sql);
			setValue(pst, values);
			res = pst.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			close(pst);
		}
		return res;
	}

	/**
	 * 使用Statement查询数据，返回List集合，数据量比较小的时候用
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql) {
		Statement stmt = null;
		ResultSet res = null;
		List<Map<String, Object>> list = null;
		try {
			stmt = getConnection().createStatement();
			res = stmt.executeQuery(sql);
			Map<String, Object> map = null;
			ResultSetMetaData rsmd = res.getMetaData();
			int rowCnt = rsmd.getColumnCount();
			list = new LinkedList<Map<String, Object>>();
			while (res.next()) {
				map = new LinkedHashMap<String, Object>(rowCnt);
				for (int i = 1; i <= rowCnt; i++) {
					map.put(rsmd.getColumnName(i), res.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			close(res);
			close(stmt);
		}
		return list;
	}

	/**
	 * 使用PreparedStatement查询数据，返回List集合，数据量比较小的时候用
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, String[] values) {
		PreparedStatement pst = null;
		ResultSet res = null;
		List<Map<String, Object>> list = null;
		try {
			pst = getConnection().prepareStatement(sql);
			setValue(pst, values);
			res = pst.executeQuery();
			Map<String, Object> map = null;
			ResultSetMetaData rsmd = res.getMetaData();
			int rowCnt = rsmd.getColumnCount();
			list = new LinkedList<Map<String, Object>>();
			while (res.next()) {
				map = new LinkedHashMap<String, Object>(rowCnt);
				for (int i = 1; i <= rowCnt; i++) {
					map.put(rsmd.getColumnName(i), res.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			close(res);
			close(pst);
		}
		return list;
	}

	private void setValue(PreparedStatement pst, String[] values)
			throws SQLException {
		try {
			for (int i = 0; i < values.length; i++) {
				pst.setString(i + 1, values[i]);
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		}
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			conn = null;
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stmt
	 */
	public void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		}
	}

	/**
	 * 关闭PreparedStatement
	 * 
	 * @param pst
	 */
	public void close(PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		}
	}

	/**
	 * 关闭ResultSet
	 * 
	 * @param rs
	 */
	public void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error(e.getCause().getMessage(), e);
		}
	}

}
