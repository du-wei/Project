package com.webapp.kafka;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * Created by king on 2015/11/27.
 */
public class ConsumerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

    	KafkaUtils.addConsumerCfg(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "121.42.145.128:9092");

        KafkaUtils.consumer((ConsumerRecord<String, String> meta) -> {
//            System.out.println(topic);
            System.out.println("-->" + meta.value());

        }, "hello", "world");

//    	KafkaUtils.addConsumerCfg("zookeeper.connect", "192.168.8.4:2181");
//    	KafkaUtils.viewTopic("hello");

    }

}
