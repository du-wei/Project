package com.spark.java;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkUtils {

	public static void ifArgs(String[] args, String tips) {
		if (args.length < 1) {
			System.err.println(tips);
			System.exit(1);
		}
	}

	public static SparkConf getConfLocal(String appName){
		SparkConf sparkConf = new SparkConf().setAppName(appName).setMaster("local");
		return sparkConf;
	}
	public static SparkConf getConf(String appName){
		SparkConf sparkConf = new SparkConf().setAppName(appName);
		return sparkConf;
	}

	public static JavaSparkContext getContextLocal(String appName){
		SparkConf sparkConf = new SparkConf().setAppName(appName).setMaster("local");
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		return ctx;
	}
	public static JavaSparkContext getContext(String appName){
		SparkConf sparkConf = new SparkConf().setAppName(appName);
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		return ctx;
	}

}
