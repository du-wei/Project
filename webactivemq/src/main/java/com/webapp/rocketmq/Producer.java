package com.webapp.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer {

	private final static String PRODUCER_GROUP_NAME = "ProducerGroupName";
	private final static String NAMESRV_ADDR = "10.18.6.168:9876";
//	private final static String NAMESRV_ADDR = "182.92.193.119:9876";
	
	private static DefaultMQProducer producer;
	static {
		startProducer();
	}
	private static DefaultMQProducer startProducer() {
		producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
		producer.setNamesrvAddr(NAMESRV_ADDR);
		producer.setInstanceName("Producer");
		try {
	        producer.start();
        } catch (MQClientException e) {
	        e.printStackTrace();
        }
		return producer;
    }
	
	public static DefaultMQProducer getProducer(){
		if(producer != null){
			return producer;
		}
		return startProducer();
	}
	
	public static void main(String[] args) throws Exception {

		Message msg = new Message("topic", "tag", "key", "hello kitty".getBytes());
		SendResult send = Producer.getProducer().send(msg);
		System.out.println(send);

	}

}
