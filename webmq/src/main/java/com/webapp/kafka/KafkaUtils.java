package com.webapp.kafka;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.admin.TopicCommand;
import kafka.admin.TopicCommand.TopicCommandOptions;
import kafka.utils.ZkUtils;

/**
 * Created by king on 2015/11/23.
 */
public class KafkaUtils {

    private static final Logger logger = LoggerFactory.getLogger(KafkaUtils.class);
    private static final String zkConn_key = "zookeeper.connect";

    private static final ExecutorService executor = Executors.newFixedThreadPool(100);
    private static final Map<String, Object> pdcCfg = new HashMap<>();
    private static final Properties csmCfg = new Properties();

    private static KafkaProducer<String, byte[]> producer;
    private static ThreadLocal<KafkaConsumer<String, byte[]>> localPdc = new ThreadLocal<>();
    static {
        pdcCfg.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        pdcCfg.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        pdcCfg.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        pdcCfg.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024 * 1024 * 5);
        pdcCfg.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        pdcCfg.put(ProducerConfig.ACKS_CONFIG, "all");

        csmCfg.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        csmCfg.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        csmCfg.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        csmCfg.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        csmCfg.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        csmCfg.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        csmCfg.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
    }

    /**
     *  cfg appy to consumer, producer
     */
    public static void addCfg(String key, Object val){
        pdcCfg.put(key, val);
        csmCfg.put(key, val);
    }

    /**
     *  cfg appy to producer
     */
    public static void addProducerCfg(String key, Object val){
        pdcCfg.put(key, val);
    }
    public static void viewProducerCfg(){
        logger.info(pdcCfg.toString());
    }

    /**
     *  cfg appy to consumer
     */
    public static void addConsumerCfg(String key, Object val){
        csmCfg.put(key, val);
    }
    public static void viewConsumerCfg(){
        logger.info(csmCfg.toString());
    }

    /**
     *  consumer topics
     *	@param handler 	processing subscribe topic
     *	@param topics 	multiple topic
     */
    public static <T extends Serializable> void consumer(TopicKeyVal<String, T> handler, String ...topics){
        consumer((ConsumerRecord<String, T> rc)->{
        	handler.apply(rc.topic(), rc.value());
        }, topics);
    }

    /**
     *  consumer topics
     *	@param handler 	processing subscribe topic
     *	@param topics 	multiple topic
     */
    @SuppressWarnings("unchecked")
	public static <T extends Serializable> void consumer(TopicRecord<ConsumerRecord<String, T>> handler, String ...topics){
        Arrays.asList(topics).forEach((topic) ->
            executor.submit(() -> {
                try {
                	getConsumer().subscribe(Arrays.asList(topic));
                    logger.info(" sub " + topic);
                    while (true) {
                        logger.info(" start poll .... " + topic);
                        ConsumerRecords<String, byte[]> records = getConsumer().poll(10000);
                        for (ConsumerRecord<String, byte[]> rc : records) {
                            handler.apply(new ConsumerRecord<String, T>(rc.topic(), rc.partition(), rc.offset(), rc.key(), (T)SerializationUtils.deserialize(rc.value())));
                        }
                    }
                } catch (Exception e) {
                    logger.error("consumer error", e);
                }
            })
        );
    }

    /**
     *  producer topic msg
     */
    public static <T extends Serializable> List<Future<RecordMetadata>> producer(String topic, List<T> msgs){
        List<Future<RecordMetadata>> fetures = new ArrayList<>();
        try{
            for(T msg : msgs){
                Future<RecordMetadata> meta = getProducer().send(new ProducerRecord<String, byte[]>(topic, SerializationUtils.serialize(msg)));
                fetures.add(meta);
            }
            getProducer().flush();
            logger.debug(" send msg to " + topic);
        }catch (Exception ex){
            logger.error("producer send error", ex);
        }
        return fetures;
    }

    /**
     *  producer topic msg
     */
    public static <T extends Serializable> Future<RecordMetadata> producer(String topic, T msg){
        return producer(topic, Arrays.asList(msg)).get(0);
    }

    private static KafkaConsumer<String, byte[]> getConsumer(){
    	if(localPdc.get() == null) {
    		localPdc.set(new KafkaConsumer<>(csmCfg));
    	}
    	return localPdc.get();
    }

    private static KafkaProducer<String, byte[]> getProducer(){
    	if(producer == null){
    		producer = new KafkaProducer<>(pdcCfg);
    	}
    	return producer;
    }

    private static ZkUtils getZkUtils(){
    	ZkConnection zkConn = new ZkConnection(csmCfg.getProperty(zkConn_key));
        ZkClient zkClient = new ZkClient(zkConn);
        ZkUtils zk = new ZkUtils(zkClient, zkConn, true);
        return zk;
    }
    public static void viewTopics(){
        TopicCommandOptions opts= new TopicCommandOptions(new String[]{});
        TopicCommand.listTopics(getZkUtils(), opts);
    }

    public static void viewTopic(String topic){
    	TopicCommandOptions opts= new TopicCommandOptions(new String[]{"--topic", topic});
        TopicCommand.describeTopic(getZkUtils(), opts);
    }

}
