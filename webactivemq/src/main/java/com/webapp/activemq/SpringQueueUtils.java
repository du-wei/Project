/**   
 * @Title: SpringQueueUtils.java 
 * @Package com.webapp.activemq 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-21 下午5:49:33 
 * @version V1.0   
 */
package com.webapp.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @ClassName: SpringQueueUtils.java
 * @Package com.webapp.activemq
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-21 下午5:49:33
 * @version V1.0
 */
public class SpringQueueUtils {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
		Destination destination = (Destination) ctx.getBean("destination");

		MyMsg mc = new MyMsg();// 生成消息

		jmsTemplate.send(destination, mc);

		TextMessage msg = null;
		boolean isContinue = true;// 是否继续接收消息
		while (isContinue) {
			msg = (TextMessage) jmsTemplate.receive(destination);
			System.out.println("收到消息 :" + msg.getText());
		}
		System.out.println("程序退出了！");

	}

}

class MyMsg implements MessageCreator {

	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createTextMessage("hello");
	}

}
