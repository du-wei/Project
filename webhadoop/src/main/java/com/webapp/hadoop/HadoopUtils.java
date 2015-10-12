package com.webapp.hadoop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Progressable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.webapp.hadoop.mapreduce.CombineClass;
import com.webapp.hadoop.mapreduce.MapperClass;
import com.webapp.hadoop.mapreduce.ReducerClass;

public class HadoopUtils {

	private static Logger logger = LogManager.getLogger(HadoopUtils.class);
	private static Configuration cfg = null;

	public static Map<String, String> viewConf() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.88.10:9000");
		conf.set("mapreduce.jobtracker.address", "hdfs://192.168.88.10:9000");
		Map<String, String> map = new TreeMap<>();
		for (Iterator<Entry<String, String>> it = conf.iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			map.put(entry.getKey(), entry.getValue());
		}
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			System.out.println(key + " --> " + map.get(key));
		}
		return map;
	}

	public static Map<String, String> getValByRegex(String regex) {
		Configuration conf = new Configuration();
		Map<String, String> map = conf.getValByRegex(regex);
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			System.out.println(key + " --> " + map.get(key));
		}
		return map;
	}

	public static String getConf(String key) {
		return getCfg().get(key);
	}

	public static Configuration getCfg() {
		if (cfg == null) {
			// Configuration cfg1 = new HdfsConfiguration();
			// Configuration cfg2 = new JobConf(cfg1);
			// Configuration cfg3 = new YarnConfiguration(cfg2);
			cfg = new Configuration();
			cfg.set("fs.defaultFS", "hdfs://192.168.88.10:9000");
		}
		return cfg;
	}

	public static void read(String path) {
		// InputStream in = null;
		FSDataInputStream fsin = null;
		try {
			FileSystem fs = getFS(path);
			fsin = fs.open(new Path(path), 4096);
			IOUtils.copyBytes(fsin, System.out, 4096, false);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			IOUtils.closeStream(fsin);
		}
	}

	public static void upload(String src, String dest) {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(src));
			FileSystem fs = getFS(dest);
			OutputStream out = fs.create(new Path(dest), new Progressable() {
				@Override
				public void progress() {
					System.out.println("*");
				}
			});
			IOUtils.copyBytes(in, out, 4096, true);
		} catch (IllegalArgumentException | IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
	}

	public static boolean exists(String file) {
		FileSystem fs = getFS();
		Path path = new Path(file);
		try {
			return fs.exists(path);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
		return false;
	}

	public static boolean mkdir(String path) {
		FileSystem fs = getFS();
		try {
			return fs.mkdirs(new Path(path));
		} catch (Exception e) {
			logger.error(e.getCause().getMessage(), e);
		}
		return false;
	}

	public static boolean del(String path) {
		FileSystem fs = getFS();
		try {
			return fs.delete(new Path(path), true);
		} catch (Exception e) {
			logger.error(e.getCause().getMessage(), e);
		}
		return false;
	}

	public static void run(Class<?> mainClz, String input, String output,
			String host) throws Exception {

		Configuration conf = new Configuration();
		// conf.setQuietMode(false);
		conf.set("mapreduce.jobtracker.address", host + ":9001");

		String[] otherArgs = new GenericOptionsParser(conf, new String[] {
				input, output }).getRemainingArgs();

		Job job = Job.getInstance(conf, "Driver Test");
		// ((JobConf) job.getConfiguration()).setJar(jarFile.toString());
		job.setJarByClass(mainClz);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setNumReduceTasks(1);
		// job.setPartitionerClass(HashPartitioner.class);

		job.setMapperClass(MapperClass.class);
		job.setCombinerClass(CombineClass.class);

		job.setReducerClass(ReducerClass.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// conf.setBoolean("mapred.output.compress", true);
		// conf.setClass("mapred.output.compression.codec",
		// GzipCodec.class,CompressionCodec.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	public static FileSystem getFS() {
		return getFS(getCfg());
	}

	public static FileSystem getFS(Configuration conf) {
		FileSystem fs = null;
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
		return fs;
	}

	public static FileSystem getFS(String hdfsBase) {
		return getFS(hdfsBase, getCfg());
	}

	public static FileSystem getFS(String hdfsBase, Configuration conf) {
		if (hdfsBase.startsWith("hdfs://.+?/")) {
			logger.error("HDFS file system path is not in conformity with the specification!");
			return null;
		}
		FileSystem fs = null;
		try {
			fs = FileSystem.get(URI.create(hdfsBase), conf);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
		return fs;
	}

	public static void getHDFSNodes() {
		FileSystem fs = HadoopUtils.getFS();
		DistributedFileSystem dfs = (DistributedFileSystem) fs;
		DatanodeInfo[] dataNodeStats = null;
		try {
			dataNodeStats = dfs.getDataNodeStats();
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
		for (int i = 0; i < dataNodeStats.length; i++) {
			System.out.println(dataNodeStats[i].getInfoAddr());
		}
	}

	public static void getFileLocal(String path) {

		FileSystem fs = HadoopUtils.getFS();
		try {
			FileStatus fileStatus = fs.getFileStatus(new Path(path));
			BlockLocation[] fbl = fs.getFileBlockLocations(fileStatus, 0,
					fileStatus.getLen());
			for (int i = 0; i < fbl.length; i++) {
				String[] hosts = fbl[i].getHosts();
				System.out.println(hosts[0]);
			}
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}

	}

}
