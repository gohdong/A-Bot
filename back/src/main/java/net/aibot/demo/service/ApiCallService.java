package net.aibot.demo.service;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCallService {
    public String callAPI(String taskFileId) {
        String uri = "http://localhost:8080/test/receiveAPI";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        return new Gson().toJson(result);
    }
}
