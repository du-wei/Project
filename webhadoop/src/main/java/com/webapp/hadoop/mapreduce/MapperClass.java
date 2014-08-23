package com.webapp.hadoop.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {

	public Text keyText = new Text("key");
	public IntWritable intValue = new IntWritable(1);

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// map前的准备工作
		super.setup(context);
		System.out.println("Mapper--->map前的准备工作");
	}

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		System.out.printf("Mapper--->map%s - %s", key, value);

		String str = value.toString();
		StringTokenizer stringTokenizer = new StringTokenizer(str);
		while (stringTokenizer.hasMoreTokens()) {
			keyText.set(stringTokenizer.nextToken());
			context.write(keyText, intValue);
		}

		System.out.println();
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		// 是收尾工作如关闭文件或者执行map()后的K-V分发等
		super.cleanup(context);
		System.out.println("Mapper--->cleanup");
	}

	@Override
	public void run(Context context) throws IOException, InterruptedException {
		// 提供了setup->map->cleanup()的执行模板
		super.run(context);
		System.out.println("Mapper--->run");
	}

}
