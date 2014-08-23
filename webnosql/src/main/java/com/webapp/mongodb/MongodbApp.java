/**   
 * @Title: MongodbApp.java 
 * @Package com.webapp.mongodb 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-4-15 下午2:11:55 
 * @version V1.0   
 */
package com.webapp.mongodb;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

/**
 * @ClassName: MongodbApp.java
 * @Package com.webapp.mongodb
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-4-15 下午2:11:55
 * @version V1.0
 */
public class MongodbApp {

	private static MongoClient client;
	private static DB db;
	private static Logger logger = Logger.getLogger(MongodbApp.class);

	@Before
	public void before() throws Exception {
		client = new MongoClient("192.168.26.130", 27017);
		db = client.getDB("king");
	}

	public static boolean insert(String coll, DBObject object) {
		DBCollection dbColl = db.getCollection(coll);
		WriteResult result = dbColl.insert(object);
		if (result.getN() > 0) {
			logger.info(" insert success ");
			return true;
		}
		return false;
	}

	public static void find(String coll, DBObject object) {
		DBCollection dbColl = db.getCollection(coll);
		DBCursor cursor = dbColl.find(object);
		while (cursor.hasNext()) {
			logger.info(cursor.next().toString());
		}
	}

	public static boolean delete(String coll, DBObject object) {
		DBCollection dbColl = db.getCollection(coll);
		WriteResult result = dbColl.remove(object);
		if (result.getN() > 0) {
			logger.info(" delete success ");
		}
		return false;
	}

	public static boolean auth(String name, String pwd) {
		return db.authenticate(name, pwd.toCharArray());
	}

	@Test
	public void testMongodb() throws Exception {
		System.out.println("auth --> " + auth("king", "king"));

		client.setWriteConcern(WriteConcern.JOURNALED);

		BasicDBObject object = new BasicDBObject();
		object.append("name", "mongodb").append("type", "db").append("age", 12);

		insert("users", object);

		find("users", object);
		// delete("users", object);

	}

}
