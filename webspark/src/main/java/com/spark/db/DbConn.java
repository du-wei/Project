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

    @Override
    public Connection apply() {
        try {
            Class.forName(DBProp.getDb().getDriver());
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load driver class", e);
        }

        Properties props = new Properties();
        props.setProperty("user", DBProp.getDb().getUsername());
        props.setProperty("password", DBProp.getDb().getPassword());

        Connection conn = null;
        try {
        	conn = DriverManager.getConnection(DBProp.getDb().getUrl(), props);
        } catch (SQLException e) {
            logger.error("Connection failed", e);
        }

        return conn;
    }

}
