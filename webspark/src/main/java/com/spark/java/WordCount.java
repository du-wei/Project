package com.spark.java;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCount {

	public static final String REGEX = "\\s+";

	public static void main(String[] args) {

		SparkUtils.ifArgs(args, "Usage: JavaWordCount <file>");

		JavaSparkContext ctx = SparkUtils.getContextLocal("hello");

		JavaRDD<String> lines = ctx.textFile(args[0], 1);

		JavaPairRDD<String, Integer> counts = lines
				.flatMap(line -> Arrays.asList(line.split(REGEX)))
				.mapToPair(word -> new Tuple2<>(word, 1))
				.reduceByKey((x, y) -> (Integer) x + (Integer) y)
				.sortByKey();

		List<Tuple2<String, Integer>> output = counts.collect();
		for (Tuple2<?, ?> tuple : output) {
			System.out.println(tuple._1() + ": " + tuple._2());
		}

		ctx.close();
		ctx.stop();
	}
}
