package com.intraedge.kafkaspringtest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ashpammi
 *
 */
@Component
public class KafkaEventConsumer {

	@KafkaListener(topics = "salesforce", groupId = "salesforce-grp-id")
	public void listen(String message) {
	    System.out.println("Received Messasge: " + message);
	}
}
