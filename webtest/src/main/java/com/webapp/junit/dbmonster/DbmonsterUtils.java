package com.webapp.junit.dbmonster;

import java.sql.SQLException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import pl.kernelpanic.dbmonster.DBMonster;
import pl.kernelpanic.dbmonster.Launcher;
import pl.kernelpanic.dbmonster.ProgressMonitor;
import pl.kernelpanic.dbmonster.ProgressMonitorAdapter;
import pl.kernelpanic.dbmonster.connection.ConnectionProvider;
import pl.kernelpanic.dbmonster.connection.SimpleConnectionProvider;
import pl.kernelpanic.dbmonster.schema.Schema;
import pl.kernelpanic.dbmonster.schema.SchemaUtil;

import com.webapp.utils.config.ConfigUtils;
import com.webapp.utils.config.PathUtils;

public class DbmonsterUtils {

	@Test
	public void testok() throws Exception {
		String schemaPath = PathUtils.getPath("dbmonster-schema.xml").toString();
		dataGenerate(schemaPath);
	}

	public static void main(String... schemaPath) throws Exception {
		System.setProperty("user.home", PathUtils.getClassPath());
		String[] args = new String[] { "-cdbmonster.properties",
				"-s" + schemaPath, "-n 100" };
		Launcher.main(args);
	}

	public static void dataGenerate(String... schemaPath) throws Exception {
		Log log = LogFactory.getLog(DbmonsterUtils.class);
		ConnectionProvider provider = getConnectionProvider();

		DBMonster dbMonster = getDBMonster(provider, log);

		ProgressMonitor monitor = getProgressMonitor();
		dbMonster.setProgressMonitor(monitor);

		for (int i = 0; i < schemaPath.length; i++) {
			Schema schema = SchemaUtil.loadSchema(schemaPath[i], log);
			dbMonster.addSchema(schema);
		}
		dbMonster.doTheJob();
	}

	private static DBMonster getDBMonster(ConnectionProvider provider, Log log) {
		DBMonster dbMonster = new DBMonster();
		dbMonster.setLogger(log);
		dbMonster.setConnectionProvider(provider);
		return dbMonster;
	}

	private static ProgressMonitor getProgressMonitor() {
		ProgressMonitor monitor = new ProgressMonitorAdapter();
		return monitor;
	}

	private static ConnectionProvider getConnectionProvider()
			throws ClassNotFoundException, SQLException {
		Configuration config = ConfigUtils.addConfig("dbmonster.properties");
		String driver = config.getString("dbmonster.jdbc.driver");
		String url = config.getString("dbmonster.jdbc.url");
		String user = config.getString("dbmonster.jdbc.username");
		String pwd = config.getString("dbmonster.jdbc.password");

		ConnectionProvider provider = new SimpleConnectionProvider(driver, url,
				user, pwd);
		provider.testConnection();
		provider.setAutoCommit(false);
		return provider;
	}

}
