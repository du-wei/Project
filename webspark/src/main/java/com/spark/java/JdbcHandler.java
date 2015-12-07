package com.spark.java;

import com.spark.db.DBProp;
import com.spark.db.DbConn;
import com.spark.db.MapResult;
import com.webapp.utils.string.Utils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.JdbcRDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import scala.reflect.ClassTag$;

import java.util.List;
import java.util.Properties;

/**
 * Created by king on 2015/11/3.
 */
public class JdbcHandler {

    public static <T> void insert(JavaSparkContext sc, DataFrame df, Function<Row, T> tran, Class<T> clz){
        SQLContext sqlCtx = new SQLContext(sc);

        Properties prop = DBProp.getDb().getProp();

        JavaRDD<T> rdd = df.javaRDD().map(row -> tran.call(row));

        DataFrame data = sqlCtx.createDataFrame(rdd, clz);

        data.write().mode(SaveMode.Append).jdbc(
                DBProp.getDb().getUrl(), Utils.toUnderline(clz.getSimpleName()), prop);
    }

    public static DataFrame select(JavaSparkContext sc, String sql, String tempTable){
        SQLContext sqlCtx = new SQLContext(sc);

        Properties prop = DBProp.getDb().getProp();

        DataFrame df = sqlCtx.read().jdbc(DBProp.getDb().getUrl(), "(" + sql + ") as " + tempTable, prop);
        return df;
    }

    public static List<Row> select(JavaSparkContext sc, String sql){
        SQLContext sqlCtx = new SQLContext(sc);
//        Map<String, String> param = new HashMap<>();
//        param.put("driver", db.getDriver());
//        param.put("url", db.getUrl());
//        param.put("user", db.getUsername());
//        param.put("password", db.getPassword());
//        param.put("dbtable", "(" + sql + ") as MyTable");
//        param.put("partitionColumn", "id");
//        param.put("lowerBound", "0");
//        param.put("upperBound", "100");
//        param.put("numPartitions", "1");
//        DataFrame df = sqlCtx.read().format("jdbc").options(param).load();

        Properties prop = DBProp.getDb().getProp();

        DataFrame df = sqlCtx.read().jdbc(DBProp.getDb().getUrl(), "(" + sql + ") as MyTable", prop);
        List<Row> records = df.collectAsList();
        return records;
    }

    public static List<Object[]> selectByRdd(JavaSparkContext sc, String sql, long lowerBound, long upperBound){

        JdbcRDD<Object[]> jdbcRDD = new JdbcRDD<>(sc.sc(), new DbConn(), sql, lowerBound, upperBound, 1, new MapResult(),
                ClassTag$.MODULE$.apply(Object[].class));

        JavaRDD<Object[]> javaRDD = JavaRDD.fromRDD(jdbcRDD, ClassTag$.MODULE$.apply(Object[].class));

        List<Object[]> collect = javaRDD.collect();

        return collect;
    }

}
