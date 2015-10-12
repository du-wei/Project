package com.webapp.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, IntWritable, Text, IntWritable> {

	public IntWritable intValue = new IntWritable(0);

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {

		int sum = 0;
		while (values.iterator().hasNext()) {
			int temp = values.iterator().next().get();
			sum += temp;

			System.out.print(key + "-" + temp + "====");
		}
		System.out.println();
		intValue.set(sum);
		context.write(key, intValue);

	}

}
