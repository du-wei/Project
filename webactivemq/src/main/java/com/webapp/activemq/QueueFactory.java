/**   
 * @Title: Sender.java 
 * @Package com.webapp.activemq 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-7 下午2:11:22 
 * @version V1.0   
 */
package com.webapp.activemq;

import org.junit.Test;

/**
 * @ClassName: Sender.java
 * @Package com.webapp.activemq
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-7 下午2:11:22
 * @version V1.0
 */
public class QueueFactory {

	@Test
	public void test() throws Exception {
		QueueUtils.sendMsg("hello");
		QueueUtils.receiveMsg();
	}

}
