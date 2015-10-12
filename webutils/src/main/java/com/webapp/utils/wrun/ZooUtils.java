package com.webapp.utils.wrun;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;


public class ZooUtils {
	
	private Watcher watcher = new Watcher() {
		@Override
		public void process(WatchedEvent event) {
			System.out.println("watcher");
		}
	};
	
	@Test
    public void testName() throws Exception {
		ZooKeeper zooKeeper = new ZooKeeper("182.92.193.119:2181", 30000, watcher);
		
		String create = zooKeeper.create("/zktest01", "zktest".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(create);
		
//		zooKeeper.delete("/zktest01", -1);
		
//		zooKeeper.getData("/zktest01", watch, null);
		
		zooKeeper.close();
    }
	
	
}
