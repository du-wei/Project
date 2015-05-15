package com.webapp.rocketmq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.StringUtils;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;

public class Consumer {
	
	private final static String CONSUMER_GROUP_NAME = "ConsumerGroupName";
	private final static String NAMESRV_ADDR = "182.92.193.119:9876";
	
	private static DefaultMQPushConsumer consumer;
	static {
		startConsumer();
	}
	
	private static DefaultMQPushConsumer startConsumer() {
		consumer = new DefaultMQPushConsumer(CONSUMER_GROUP_NAME);
		consumer.setNamesrvAddr(NAMESRV_ADDR);
		consumer.setInstanceName("Consumber");
		
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println("xxxx");
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		
		try {
	        consumer.start();
        } catch (MQClientException e) {
	        e.printStackTrace();
        }
		return consumer;
    }
	
	public static DefaultMQPushConsumer getConsumer() {
	    if(consumer != null){
	    	return consumer;
	    }
	    return startConsumer();
    }
	
	public static void main(String[] args) throws Exception {
		Consumer.getConsumer().subscribe("topic", "*");
		Consumer.getConsumer().registerMessageListener(new MessageListenerConcurrently() {
			
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				
				System.out.println(Thread.currentThread().getName()
			            + " Receive New Messages: " + msgs.size());
				
				MessageExt msg = msgs.get(0);
				
				String txt = StringUtils.newStringUtf8(msg.getBody());
				System.out.println(txt);
				
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
    }
	
	private static final Map<MessageQueue, Long> offseTable = new HashMap<MessageQueue, Long>();
	public static void pull() throws Exception {
		DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(CONSUMER_GROUP_NAME);
		consumer.start();
		
		try {
			MessageQueue mq = new MessageQueue();
			mq.setQueueId(0);
			mq.setTopic("TopicTest3");
			mq.setBrokerName("vivedeMacBook-Pro.local");
			long offset = 26;
			long beginTime = System.currentTimeMillis();
			PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, offset, 32);
			System.out.println(System.currentTimeMillis() - beginTime);
			System.out.println(pullResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		consumer.shutdown();
		
//		Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("TopicTest");
//		for (MessageQueue mq : mqs) {
//			System.out.println("Consume from the queue: " + mq);
//			SINGLE_MQ:
//			while (true) {
//				try {
//					PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
//					System.out.println(pullResult);
//					putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
//					switch (pullResult.getPullStatus()) {
//						case FOUND:
//							// TODO
//							break;
//						case NO_MATCHED_MSG:
//							break;
//						case NO_NEW_MSG:
//							break SINGLE_MQ;
//						case OFFSET_ILLEGAL:
//							break;
//						default:
//							break;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		consumer.shutdown();
    }

	private static void putMessageQueueOffset(MessageQueue mq, long offset) {
		offseTable.put(mq, offset);
	}

	private static long getMessageQueueOffset(MessageQueue mq) {
		Long offset = offseTable.get(mq);
		if (offset != null) return offset;
		return 0;
	}
	
}
