package com.webapp.hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.webapp.hadoop.mapreduce.MapperClass;
import com.webapp.hadoop.mapreduce.ReducerClass;

public class MrunitUtils {

	private MapDriver<Object, Text, Text, IntWritable> mapDriver;
	private ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	private MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	public static void testMapper(Mapper<Object, Text, Text, IntWritable> mapClz)
			throws Exception {

		MapDriver<Object, Text, Text, IntWritable> mapDriver = MapDriver
				.newMapDriver(mapClz);
		mapDriver.withInput(new Text("ok"), new Text("this is a test\nhello"))
				.withOutput(new Text("this"), new IntWritable(1))
				.withOutput(new Text("is"), new IntWritable(1))
				.withOutput(new Text("a"), new IntWritable(1))
				.withOutput(new Text("test"), new IntWritable(1))
				.withOutput(new Text("hello"), new IntWritable(1)).runTest();

	}

	public static void main(String[] args) throws Exception {
		// MrunitUtils.testMapper(new MapperClass());
	}

	@Before
	public void before() {
		// mapDriver= MapDriver.newMapDriver(new MapperClass());
		// reduceDriver = ReduceDriver.newReduceDriver(new ReducerClass());
		// mapReduceDriver = MapReduceDriver.newMapReduceDriver(new
		// MapperClass(), new ReducerClass());
	}

	@Test
	public void testMappers() throws Exception {
		mapDriver.withInput(new Text("ok"), new Text("this is a test\nhello"))
				.withOutput(new Text("this"), new IntWritable(1))
				.withOutput(new Text("is"), new IntWritable(1))
				.withOutput(new Text("a"), new IntWritable(1))
				.withOutput(new Text("test"), new IntWritable(1))
				.withOutput(new Text("hello"), new IntWritable(1)).runTest();
	}

	@Test
	public void testReduces() throws Exception {
		List<IntWritable> list = new ArrayList<>();
		list.add(new IntWritable(2));
		list.add(new IntWritable(3));

		reduceDriver.withInput(new Text("sss"), list)
				.withOutput(new Text("sss"), new IntWritable(5)).runTest();
	}

	@Test
	public void testMapReduces() throws Exception {

		mapReduceDriver.withInput(new Text("sss"), new Text("this"))
				.withOutput(new Text("this"), new IntWritable(1)).runTest();
	}

}
