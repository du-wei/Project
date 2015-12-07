package com.webapp.kafka;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * Created by king on 2015/11/27.
 */
public class ProducerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        KafkaUtils.addProducerCfg(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "121.42.145.128:9092");
        KafkaUtils.addProducerCfg(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.8.4:9092");

        long start = System.currentTimeMillis();
        for(int i=0; i<100; i++){
        	System.out.println(i);
        	KafkaUtils.producer("hello", "hello world");
        }
        long stop = System.currentTimeMillis();
        System.out.println(stop-start);
    }

}
