package com.spark.java;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.JdbcRDD;

import com.spark.db.DbConn;
import com.spark.db.MapResult;

import scala.reflect.ClassTag$;

public class SparkDataSource {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.18.6.91:3306/meng800_show_test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1q2w3e4r";

	public static void jdbc(JavaSparkContext sc, String sql){

		DbConn db = new DbConn("jdbc:mysql://182.92.193.119:3306/common", "root", "kingadmin");
		JdbcRDD<Object[]> jdbcRDD =
                new JdbcRDD<>(sc.sc(), db, "select * from p2p where list_id >= ? and list_id <= ?",
                		67355, 67370, 1, new MapResult(), ClassTag$.MODULE$.apply(Object[].class));
		JavaRDD<Object[]> javaRDD = JavaRDD.fromRDD(jdbcRDD, ClassTag$.MODULE$.apply(Object[].class));
		List<String> collect = javaRDD.map((x) -> x[1] + " - " + x[2]).collect();
		collect.forEach((x) -> System.out.println(x));

	}

}
