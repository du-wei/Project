package com.spark.java;

import com.spark.db.DBProp;
import com.spark.db.Person;
import com.webapp.utils.config.PathUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import java.nio.file.Path;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		ConfUtils.ifArgs(args, 0, "Usage: JavaWordCount <file>");
		JavaSparkContext sc = ConfUtils.getContextLocal("hello");

//        JavaRDD<String> ty = sc.textFile("tachyon://10.20.88.11:19998/a");
//        ty.saveAsTextFile("tachyon://10.20.88.11:19998/b");

        System.out.println("");

//        algo(sc, args);

//        jdbcRdd(sc);

//        insert(sc, "E:/dev/jdbc.json");

//        select(sc);

//        testDB(sc);

		sc.stop();
	}

    public static void algo(JavaSparkContext sc, String[] args){
		JavaRDD<String> lines = sc.textFile(args[0], 1);
		JavaPairRDD<String, Integer> counts = AlgosHandler.wordCount(lines);
    }

    public static void jdbcRdd(JavaSparkContext sc){
        String sql = "select id, pername, position from person where id > ? and id < ?";
		List<Object[]> jdbc = JdbcHandler.selectByRdd(sc, sql, 0, 100);
		System.out.println(jdbc);
    }

    public static void insert(JavaSparkContext sc, String path){
        DataFrame json = SourceHandler.json(sc, path);
        JdbcHandler.insert(sc, json, row -> new Person(0, row.getString(0), row.getString(1)), Person.class);
    }

    public static void select(JavaSparkContext sc){
        String sql = "select id, pername, position from person where id > 100 limit 10";
        List<Row> rows = JdbcHandler.select(sc, sql);
        for (Row row : rows) {
			System.out.println(row);
		}
    }

    public static void testDB(JavaSparkContext sc){
        Path json = PathUtils.getPath("jdbc.json");
        DataFrame db = SourceHandler.json(sc, json.toString());
        db.write().mode(SaveMode.Overwrite).jdbc(DBProp.getDb().getUrl(), "jdbc_test", DBProp.getDb().getProp());
    }
}
