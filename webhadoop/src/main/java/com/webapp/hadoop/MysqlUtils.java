package com.webapp.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class MysqlUtils {

	private static String driverName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://192.168.88.128:3306/hive";
	private static String userName = "root";
	private static String passwd = "king";

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		DBConfiguration.configureDB(conf, driverName, dbUrl, userName, passwd);

		Job job = Job.getInstance(conf, "Driver Test");

		String[] fields = { "id", "name", "age", "descs" };
		DBInputFormat.setInput(job, StudentRecord.class, "student", null, "id",
				fields);
	}

}

class StudentRecord implements Writable, DBWritable {

	private int id;
	private String name;
	private int age;
	private String descs;

	@Override
	public void readFields(ResultSet result) throws SQLException {
		this.id = result.getInt(1);
		this.name = result.getString(2);
		this.age = result.getInt(3);
		this.descs = result.getString(4);
	}

	@Override
	public void write(PreparedStatement stmt) throws SQLException {
		stmt.setInt(1, this.id);
		stmt.setString(2, this.name);
		stmt.setInt(3, this.age);
		stmt.setString(4, this.descs);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.id = in.readInt();
		this.name = Text.readString(in);
		this.age = in.readInt();
		this.descs = Text.readString(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.write(this.id);
		Text.writeString(out, this.name);
		out.write(this.age);
		Text.writeString(out, this.descs);
	}

}
