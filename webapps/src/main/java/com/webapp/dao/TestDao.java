package com.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDao {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.95:1521:myoracle", "scott",
					"admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = TestDao.getConnection();

		try {
			Statement state = conn.createStatement();
			ResultSet result = state
					.executeQuery("SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL");
			while (result.next()) {
				System.out.println(result.getString(1));
			}
			System.out.println("hello");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Prevent instantiation */
	private TestDao() {
	}

}
