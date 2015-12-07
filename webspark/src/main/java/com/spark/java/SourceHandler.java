package com.spark.java;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class SourceHandler {

	public static DataFrame json(JavaSparkContext sc, String jsonPath) {
		SQLContext sqlCtx = new SQLContext(sc);
		DataFrame df = sqlCtx.read().json(jsonPath);

		return df;
	}

}
