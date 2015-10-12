package com.webapp.kafka;

import kafka.admin.TopicCommand;

public class KafkaUtils {
	
	public static void main(String[] args) {
	    createTopic();
    }
	
	public static void createTopic() {
		String[] options = new String[]{  
		    "--create",  
		    "--zookeeper",  
		    "10.18.6.168:2181",  
		    "--replication-factor",  
		    "1",  
		    "--partitions",  
		    "1",  
		    "--topic",  
		    "my_topic_name",  
		};  
		TopicCommand.main(options);
		

//	    String[] optionsd = new String[]{  
//	        "--list",  
//	        "--zookeeper",  
//	        "10.18.6.168:2181"  
//	    };  
//	    TopicCommand.main(optionsd);  
		
		
		
    }
	
}
