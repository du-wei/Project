package com.webapp.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class ProducerDemo {
	
	
	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("metadata.broker.list", "10.18.6.168:9092");
//        props.put("metadata.broker.list", "182.92.193.119:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "-1");
		
	    ProducerConfig config = new ProducerConfig(props);
	  
	    Producer<String, String> producer = new Producer<String, String>(config);
	    
	    KeyedMessage<String, String> msg = new KeyedMessage<String, String>("yar_data", "1111");
	    
	    producer.send(msg);
	    
	    producer.close();
		
    }
	
}
