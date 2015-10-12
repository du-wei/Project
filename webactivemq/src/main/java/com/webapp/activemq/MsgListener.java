/**   
 * @Title: MsgListener.java 
 * @Package com.webapp.activemq 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-21 下午4:52:19 
 * @version V1.0   
 */
package com.webapp.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ClassName: MsgListener.java
 * @Package com.webapp.activemq
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-21 下午4:52:19
 * @version V1.0
 */
public class MsgListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		if (msg instanceof TextMessage) {
			try {
				System.out.println(((TextMessage) msg).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
