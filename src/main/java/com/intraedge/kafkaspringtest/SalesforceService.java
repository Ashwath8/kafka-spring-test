package com.intraedge.kafkaspringtest;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class SalesforceService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${salesforce.clientId}")
    private String SALESFORCE_CLIENTID;

    @Value("${salesforce.clientSecret}")
    private String SALESFORCE_CLIENTSECRET;

    @Value("${salesforce.username}")
    private String SALESFORCE_USERNAME;

    @Value("${salesforce.password}")
    private String SALESFORCE_PASSWORD;

    @Value("${salesforce.grantType}")
    private String SALESFORCE_AUTH_GRANT_TYPE;

    @Value("${salesforce.tokenUrl}")
    private String SALESFORCE_TOKEN_URL;

    @Value("${salesforce.authorizationUrl}")
    private String SALESFORCE_AUTH_URL;

    @Value("${salesforce.redirectUrl}")
    private String SALESFORCE_REDIRECT_URL;

    static final String CONTACT_URL = "https://na85.salesforce.com/services/data/v45.0/sobjects/Contact";

    @SuppressWarnings("unchecked")
    public Map<String, String> authenticate() {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", SALESFORCE_AUTH_GRANT_TYPE);
        map.add("client_id", SALESFORCE_CLIENTID);
        map.add("client_secret", SALESFORCE_CLIENTSECRET);
        map.add("username", SALESFORCE_USERNAME);
        map.add("password", SALESFORCE_PASSWORD);

        Map<String, String> token = (Map<String, String>) restTemplate.postForObject(SALESFORCE_TOKEN_URL, map,
                Map.class);
        return token;
    }

    public void getContacts(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(CONTACT_URL, HttpMethod.GET, entity, String.class);
        System.out.println(response.getStatusCodeValue());
        System.out.println(response.getBody());
    }
}
