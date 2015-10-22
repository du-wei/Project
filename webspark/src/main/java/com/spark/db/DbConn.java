package com.spark.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.runtime.AbstractFunction0;

public class DbConn extends AbstractFunction0<Connection> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(DbConn.class);

    private String driver;
    private String url;
    private String username;
    private String password;

    public DbConn(String url, String userName, String password) {
        this("com.mysql.jdbc.Driver", url, userName, password);
    }

    public DbConn(String driver, String url, String userName, String password) {
        this.driver = driver;
        this.url = url;
        this.username = userName;
        this.password = password;
    }

    @Override
    public Connection apply() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load driver class", e);
        }

        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        Connection conn = null;
        try {
        	conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            logger.error("Connection failed", e);
        }

        return conn;
    }
}
