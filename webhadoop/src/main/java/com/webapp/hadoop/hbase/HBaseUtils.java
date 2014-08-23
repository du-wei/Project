package com.webapp.hadoop.hbase;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hdfs.server.namenode.status_jsp;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.webapp.hadoop.HadoopUtils;
import com.webapp.utils.file.EncodeUtils;

public class HBaseUtils {
	private static Logger logger = Logger.getLogger(HBaseUtils.class);

	@Test
	public void testName() throws Exception {
		// HBaseUtils.getData("blog", "1");
		System.out.println("hello");
	}

	public static void main(String[] args) throws Throwable {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum",
				"192.168.88.128,192.168.88.129,192.168.88.130");
		conf = HBaseConfiguration.create(conf);

		HBaseAdmin admin = new HBaseAdmin(conf);
		TableName[] listTables = admin.listTableNames();
		for (TableName tableName : listTables) {
			System.out.println(tableName.getNameAsString());
		}
		admin.close();

	}

	public static boolean putOrUpd(String tableName, String rowKey,
			Map<String, String> qualVal) {
		HTable table = getTable(tableName);
		String separator = ":";

		Put put = new Put(Bytes.toBytes(rowKey));
		Iterator<String> iterator = qualVal.keySet().iterator();
		for (; iterator.hasNext();) {
			String qualifier = iterator.next();
			put.add(Bytes.toBytes(qualifier.split(separator)[0]),
					Bytes.toBytes(qualifier.split(separator)[1]),
					Bytes.toBytes(qualVal.get(qualifier)));
		}

		try {
			table.put(put);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
			return false;
		} finally {
			closeHTable(table);
		}
		return true;
	}

	public static boolean delData(String tableName, String rowKey) {
		return delData(tableName, rowKey, new String[] {});
	}

	public static boolean delData(String tableName, String rowKey,
			String... qualifier) {
		HTable table = getTable(tableName);

		Delete del = new Delete(Bytes.toBytes(rowKey));
		for (String qual : qualifier) {
			String[] qualVal = qual.split(":");
			del.deleteColumns(Bytes.toBytes(qualVal[0]),
					Bytes.toBytes(qualVal[0]));
		}
		try {
			table.delete(del);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
			return false;
		} finally {
			closeHTable(table);
		}
		return true;

	}

	public static void getData(String tableName, String rowKey) {
		HTable table = getTable(tableName);
		try {
			Get get = new Get(Bytes.toBytes(rowKey));
			Result result = table.get(get);
			listCell(result);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			closeHTable(table);
		}
	}

	public static void scanDate(String tableName) {
		HTable table = getTable(tableName);
		Scan scan = new Scan();
		ResultScanner rs = null;
		try {
			rs = table.getScanner(scan);
			for (Result r : rs) {
				listCell(r);
			}
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		} finally {
			rs.close();
			closeHTable(table);
		}
	}

	public static void listCell(Result result) {
		for (Cell cell : result.listCells()) {
			System.out.print(Bytes.toString(CellUtil.cloneFamily(cell)));
			System.out.print(":"
					+ Bytes.toString(CellUtil.cloneQualifier(cell)));
			System.out.print("-->" + Bytes.toString(CellUtil.cloneValue(cell)));
			System.out.print("-->" + cell.getTimestamp());
			System.out.println();
		}
	}

	public static HTable getTable(String tableName) {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum",
				"192.168.88.128,192.168.88.129,192.168.88.130");
		conf = HBaseConfiguration.create(conf);

		HTable table = null;
		try {
			table = new HTable(conf, tableName);
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
		return table;
	}

	public static void closeHTable(HTable table) {
		try {
			table.close();
		} catch (IOException e) {
			logger.error(e.getCause().getMessage(), e);
		}
	}

	public static void testNames() throws Exception {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum",
				"192.168.88.128,192.168.88.129,192.168.88.130");
		conf = HBaseConfiguration.create(conf);

		HBaseAdmin admin = new HBaseAdmin(conf);
		// HTable table = new HTable(conf, "mytable");

		HTableDescriptor desc = new HTableDescriptor(
				TableName.valueOf("mytable"));
		desc.addFamily(new HColumnDescriptor("hcol1"));
		desc.addFamily(new HColumnDescriptor("hcol2"));

		admin.createTable(desc);
		//
		// // 查询nickname的多个(本示例为2个)版本值
		// Get get4 = new Get(Bytes.toBytes("1"));
		// get4.addColumn(Bytes.toBytes("author"), Bytes.toBytes("nickname"));
		// get4.setMaxVersions(2);
		// List<Cell> results = table.get(get4).listCells();
		// for(Cell cell : results){
		// System.out.println("Family:"+Bytes.toString(CellUtil.cloneFamily(cell)));
		// System.out.println("Qualifier:"+Bytes.toString(CellUtil.cloneQualifier(cell)));
		// System.out.println("Value:"+Bytes.toString(CellUtil.cloneValue(cell)));
		// System.out.println("Timestamp:"+cell.getTimestamp());
		// }
		//
		//
		// // 删除指定column
		// Delete deleteColumn = new Delete(Bytes.toBytes("1"));
		// deleteColumn.deleteColumns(Bytes.toBytes("author"),
		// Bytes.toBytes("nickname"));
		// table.delete(deleteColumn);
		// assertThat(table.get(get4).listCells(), null);
		// // 删除所有column
		// Delete deleteAll = new Delete(Bytes.toBytes("1"));
		// table.delete(deleteAll);
		// assertThat(table.getScanner(scan).next(), null);
		//
		// admin.disableTable("blog");
		// admin.deleteTable("blog");
		// table.close();
		admin.close();
	}

}
