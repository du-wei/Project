package com.spark.db;

import com.webapp.utils.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DBProp {

    private static final Logger logger = LoggerFactory.getLogger(DBProp.class);

    private static class SingleHolder {
        private static DBProp db = new DBProp();
    }

    public static DBProp getDb() {
        return SingleHolder.db;
    }

    private DBProp() {
        Properties props = ConfigUtils.read("build_dev.properties");

        this.driver = props.getProperty("driver");
        this.url = props.getProperty("url");
        this.username = props.getProperty("username");
        this.password = props.getProperty("password");

        Properties prop = new Properties();
        prop.put("driver", this.driver);
        prop.put("url", this.url);
        prop.put("user", this.username);
        prop.put("password", this.password);
        this.prop = prop;
    }

    private String driver;
    private String url;
    private String username;
    private String password;
    private Properties prop;

    public Properties getProp() {
        return prop;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}