package com.webapp.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerDemo {
	
	public static void main(String[] args) {
		Properties props = new Properties();  
		props.put("zookeeper.connect", "10.18.6.168:2181");  
//        props.put("zookeeper.connect", "182.92.193.119:2181");  
        props.put("zookeeper.connectiontimeout.ms", "1000000");  
        props.put("group.id", "yar_group");  
        
        ConsumerConfig consumerConfig = new ConsumerConfig(props);  
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);  
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();  
        map.put("yar_data", 1);  
        
        Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams =  
                consumerConnector.createMessageStreams(map);  
        
        List<KafkaStream<byte[], byte[]>> streams = topicMessageStreams.get("yar_data");
        
        ExecutorService executor = Executors.newFixedThreadPool(1);
        
        for(final KafkaStream<byte[], byte[]> stream : streams){
        	executor.submit(new Runnable() {
				@Override
				public void run() {
					
					ConsumerIterator<byte[], byte[]> it = stream.iterator();
			        while (it.hasNext()){
			        	System.out.println("Thread " + 1 + ": " + new String(it.next().message()));
			        }
			        System.out.println("Shutting down Thread: " + 1);
				}
			});
        }
    }
	
}
