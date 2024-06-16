package com.example.greenapiproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GreenApiService {

    private final RestTemplate restTemplate;

    private final String base_url = "https://7103.api.greenapi.com";

    public GreenApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getSettings(String idInstance, String apiTokenInstance){
        String url = base_url + "/waInstance" + idInstance + "/getSettings/" + apiTokenInstance;
        return restTemplate.getForObject(url, String.class);
    }

    public String getStateInstance(String idInstance, String apiTokenInstance){
        String url = base_url + "/waInstance" + idInstance + "/getStateInstance/" + apiTokenInstance;
        return restTemplate.getForObject(url, String.class);
    }

    public String sendMessage(String idInstance, String apiTokenInstance, String chatId, String message){
        String url = base_url + "/waInstance" + idInstance + "/sendMessage/" + apiTokenInstance;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonBody = String.format("{\"chatId\":\"%s\", \"message\":\"%s\"}", chatId, message);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();
    }

    public String sendFileByUrl(String idInstance, String apiTokenInstance, String chatId, String urlFile, String fileName){
        String apiUrl = base_url + "/waInstance" + idInstance + "/sendFileByUrl/" + apiTokenInstance;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonBody = String.format("{\"chatId\":\"%s\", \"urlFile\":\"%s\", \"fileName\":\"%s\"}", chatId, urlFile, fileName);
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
        return response.getBody();
    }
}
