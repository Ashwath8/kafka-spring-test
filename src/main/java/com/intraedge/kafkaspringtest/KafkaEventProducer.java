package com.intraedge.kafkaspringtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ashpammi
 *
 */

@Component
public class KafkaEventProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * Simply produce message to Kafka
	 * @param message
	 * @param topicName
	 */
	public void produceMessage(String message, String topicName) {
		kafkaTemplate.send(topicName, message);
	}
	
}
