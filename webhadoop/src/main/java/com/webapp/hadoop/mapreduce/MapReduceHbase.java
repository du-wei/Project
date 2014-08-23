package com.webapp.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;

public class MapReduceHbase {

	public static class MyMapper extends
			TableMapper<ImmutableBytesWritable, ImmutableBytesWritable> {

		@Override
		protected void map(ImmutableBytesWritable row, Result values,
				Context context) throws IOException, InterruptedException {
			ImmutableBytesWritable value = null;
			String[] tags = null;
			for (Cell cell : values.listCells()) {
				if ("author".equals(Bytes.toString(CellUtil.cloneFamily(cell)))
						&& "nickname".equals(Bytes.toString(CellUtil
								.cloneQualifier(cell)))) {
					value = new ImmutableBytesWritable(
							CellUtil.cloneValue(cell));
				}
				if ("article"
						.equals(Bytes.toString(CellUtil.cloneFamily(cell)))
						&& "tags".equals(Bytes.toString(CellUtil
								.cloneQualifier(cell)))) {
					tags = Bytes.toString(CellUtil.cloneValue(cell)).split(",");
				}
			}
			for (int i = 0; i < tags.length; i++) {
				ImmutableBytesWritable key = new ImmutableBytesWritable(
						Bytes.toBytes(tags[i].toLowerCase()));
				try {
					context.write(key, value);
				} catch (InterruptedException e) {
					throw new IOException(e);
				}
			}

		}
	}

	public static class MyReducer
			extends
			TableReducer<ImmutableBytesWritable, ImmutableBytesWritable, ImmutableBytesWritable> {

		@Override
		protected void reduce(ImmutableBytesWritable key,
				Iterable<ImmutableBytesWritable> values, Context context)
				throws IOException, InterruptedException {

			String friends = "";
			for (ImmutableBytesWritable val : values) {
				friends += (friends.length() > 0 ? "," : "")
						+ Bytes.toString(val.get());
			}
			Put put = new Put(key.get());
			put.add(Bytes.toBytes("person"), Bytes.toBytes("nicknames"),
					Bytes.toBytes(friends));
			context.write(key, put);
		}

	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf = HBaseConfiguration.create(conf);
		Job job = Job.getInstance(conf, "HBase_FindFriend");
		job.setJarByClass(MapReduceHbase.class);
		Scan scan = new Scan();
		scan.addColumn(Bytes.toBytes("author"), Bytes.toBytes("nickname"));
		scan.addColumn(Bytes.toBytes("article"), Bytes.toBytes("tags"));
		TableMapReduceUtil
				.initTableMapperJob("blog", scan, MyMapper.class,
						ImmutableBytesWritable.class,
						ImmutableBytesWritable.class, job);
		TableMapReduceUtil.initTableReducerJob("tag_friend", MyReducer.class,
				job);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
