package com.spark.java;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkData {

	public static void data(JavaSparkContext ctx){
		JavaRDD<String> input = ctx.textFile("E:\\dev\\people.txt");
//		SQLContext sqlCtx = new org.apache.spark.sql.SQLContext(ctx);

//		JavaSQLContext sqlCtx = new JavaSQLContext(ctx);

	}

}
