package com.spark.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class WordCount {

	public static final String REGEX = "\\s+";

	public static void main(String[] args) {

		SparkUtils.ifArgs(args, 0, "Usage: JavaWordCount <file>");

		JavaSparkContext sc = SparkUtils.getContextLocal("hello");

//		JavaRDD<String> lines = sc.textFile(args[0], 1);
//		JavaPairRDD<String, Integer> counts = SparkDataHandle.wordCount(lines);

		SQLContext sqlContext = new SQLContext(sc);
		Map<String, String> options = new HashMap<>();
        options.put("driver", "com.mysql.jdbc.Driver");
        options.put("url", "jdbc:mysql://182.92.193.119:3306/common?user=root&password=kingadmin");
        options.put("dbtable","(select list_id, title from p2p limit 10) as hello");
        options.put("partitionColumn", "list_id");
        options.put("lowerBound", "67355");
        options.put("upperBound", "67370");
        options.put("numPartitions", "1");

        //Load MySQL query result as DataFrame
        DataFrame jdbcDF = sqlContext.read().format("jdbc").options(options).load();

        List<Row> records = jdbcDF.collectAsList();

        for (Row row : records) {
            System.out.println(row);
        }


//		List<Tuple2<String, Integer>> output = counts.collect();
//		for (Tuple2<?, ?> tuple : output) {
//			System.out.println(tuple._1() + ": " + tuple._2());
//		}

//		sc.close();
		sc.stop();
	}
}
