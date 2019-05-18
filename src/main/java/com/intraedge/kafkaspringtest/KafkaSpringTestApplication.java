package com.intraedge.kafkaspringtest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KafkaSpringTestApplication implements ApplicationRunner {

    @Autowired
    KafkaEventProducer producer;

    @Autowired
    SalesforceService salesforceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub
        Map<String, String> token = salesforceService.authenticate();
        String accessToken = token.get("access_token");
        System.out.println(accessToken);
        salesforceService.getContacts(accessToken);
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringTestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
