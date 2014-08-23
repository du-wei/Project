/**   
 * @Title: Sender.java 
 * @Package com.webapp.activemq 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-7 下午2:11:22 
 * @version V1.0   
 */
package com.webapp.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.pool.PoolUtils;

/**
 * @ClassName: Sender.java
 * @Package com.webapp.activemq
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-7 下午2:11:22
 * @version V1.0
 */
public class QueueUtils {

	private static ConnectionFactory factory;
	private static Connection conn;
	private static Session session;
	static {
		init();
	}

	public static void sendMsg(String msg) throws Exception {
		TextMessage textMsg = getQueueSession("").createTextMessage(msg);
		sendMsg(textMsg);
	}

	public static void sendMsg(Message msg) throws Exception {
		Session session = getQueueSession("");

		// Destination ：消息的目的地;消息发送给谁.
		Destination dest = session.createQueue("first");
		// MessageProducer：消息发送者
		MessageProducer msgProducer = session.createProducer(dest);

		// 设置不持久化，此处学习，实际根据项目决定
		msgProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		msgProducer.send(msg);
		session.commit();
		System.out.println("send ... ");
	}

	public static void receiveMsg() throws Exception {
		Session session = getQueueSession("");
		Destination dest = session.createQueue("first");
		MessageConsumer msgConsumer = session.createConsumer(dest);
		// msgConsumer.setMessageListener(new MsgListener());
		while (true) {
			TextMessage msg = (TextMessage) msgConsumer.receive(100);
			if (msg != null) {
				System.out.println(msg.getText());
			} else {
				session.commit();
				break;
			}
		}
	}

	public static Session getQueueSession(String name) throws Exception {
		if (session == null) {
			session = conn.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
		}
		return session;
	}

	public static void init() {
		try {
			factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			conn = factory.createConnection();
			conn.start();
			session = conn
					.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
