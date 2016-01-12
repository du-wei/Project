/**
 * @Title: MongodbApp.java
 * @Package com.webapp.mongodb
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-4-15 下午2:11:55
 * @version V1.0
 */
package com.webapp.utils.base;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.ucf.meng.datacenter.model.AIC;

/**
 * @ClassName: MongodbApp.java
 * @Package com.webapp.mongodb
 * @Description: TODO 类型描述
 * @author king
 * @date 2015-4-15 下午2:11:55
 * @version V1.0
 */
public class MongodbApp {

	private static MongoClient client;
	private static MongoDatabase db;
	private static final Logger logger = LoggerFactory.getLogger(MongodbApp.class);

	static {
//		client = new MongoClient("192.168.26.130", 27017);
		client = new MongoClient("10.20.78.91", 27017);
		db = client.getDatabase("out");
	}

	public static MongoCollection<Document> getColl(String coll) {
		MongoCollection<Document> clt = db.getCollection(coll);
		return clt;
	}

	public static void insertOne(String coll, Document doc) {
		MongoCollection<Document> clt = getColl(coll);
		clt.insertOne(doc);
	}
	public static void insertMany(String coll, List<Document> docs) {
		MongoCollection<Document> clt = getColl(coll);
		clt.insertMany(docs);
	}
	public static long count(String coll) {
		return getColl(coll).count();
	}

	public static FindIterable<Document> find(String coll, Bson bson) {
		MongoCollection<Document> clt = getColl(coll);
		FindIterable<Document> docs = clt.find(bson);
		return docs;
	}

	public static boolean delete(String coll, Bson bson) {
		MongoCollection<Document> clt  = getColl(coll);
		DeleteResult result = clt.deleteOne(bson);
		if (result.getDeletedCount() > 0) {
			logger.info(" delete success ");
			return true;
		}
		return false;
	}

	public static boolean auth(String name, String pwd) {
//		return db.authenticate(name, pwd.toCharArray());
		return false;
	}

}
