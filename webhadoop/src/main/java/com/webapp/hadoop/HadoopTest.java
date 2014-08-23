package com.webapp.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopTest {
	// hadoop http://192.168.88.128:8088/cluster
	// hadoop node http://192.168.88.128:8042/node
	// MapReduce的页面 http://192.168.88.128:50030
	// HDFS的页面 http://192.168.88.128:50070/dfshealth.jsp
	// http://192.168.88.128:50075/
	// hbase http://192.168.88.128:60010/

	private static String HDFS = "hdfs://192.168.88.10:9000";

	public static void main(String[] args) throws Exception {
		// ToolRunner.run(tool, args);
		// Partition Combiner Shuffle
		String input = HDFS + "/input/input.txt";
		String output = HDFS + "/output/" + System.currentTimeMillis();

		// HadoopUtils.run(HadoopTest.class, input, output, host);

		HadoopUtils.getHDFSNodes();

	}

}
