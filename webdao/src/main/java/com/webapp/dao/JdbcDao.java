package com.webapp.dao;

import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

public class JdbcDao {

	public static void main(String[] args) throws Exception {
		DriverManager.registerDriver(new Driver());
		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");

	}

}
