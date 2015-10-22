package com.spark.java;

import java.util.Arrays;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import scala.Tuple2;

public class SparkDataHandle {

	public static final String REGEX = "\\s+";

	public static JavaPairRDD<String, Integer> wordCount(JavaRDD<String> javaRDD) {
		JavaPairRDD<String, Integer> counts = javaRDD
				.flatMap(line -> Arrays.asList(line.split(REGEX)))
				.mapToPair(word -> new Tuple2<>(word, 1))
				.reduceByKey((x, y) -> (Integer) x + (Integer) y)
				.sortByKey();

		return counts;
	}

}
