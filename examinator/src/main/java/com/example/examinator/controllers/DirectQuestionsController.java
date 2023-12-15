package com.example.examinator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class DirectQuestionsController {

    private final RestTemplate baseRestTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    DirectQuestionsController(@Qualifier("baseRestTemplate") RestTemplate baseRestTemplate) {
        this.baseRestTemplate = baseRestTemplate;
    }

    @PostConstruct
    public void init() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("questions-list")
    public String getQuestionsList() throws JsonProcessingException {
        log.info("GET /questions-list");
        CompletableFuture<List<String>> jsQuestions = CompletableFuture.supplyAsync(() -> {
            try {
                String serviceUrl = getServiceUrl("js-questions-service");
                log.info("GET js questions list from URL: {}", serviceUrl);
                String responseJson =  baseRestTemplate.getForObject(serviceUrl, String.class);
                return objectMapper.readValue(responseJson, new TypeReference<>() {});
            } catch (Exception ex) {
                log.error("Error with getting JS questions list");
                return Collections.emptyList();
            }
        });
        CompletableFuture<List<String>> javaQuestions = CompletableFuture.supplyAsync(() -> {
            try {
                String serviceUrl = getServiceUrl("java-questions-service");
                log.info("GET java questions list from URL: {}", serviceUrl);
                String responseJson =  baseRestTemplate.getForObject(serviceUrl, String.class);
                return objectMapper.readValue(responseJson, new TypeReference<>() {});
            } catch (Exception ex) {
                log.error("Error with getting Java questions list");
                return Collections.emptyList();
            }
        });

        CompletableFuture<List<String>> questionsFuture = jsQuestions.thenCombine(javaQuestions,
                (jsResult, javaResult) -> {
                    jsResult.addAll(javaResult);
                    return jsResult;
                });

        List<String> questions = questionsFuture.join();
        log.info("Return questions list: {}", questions);
        return objectMapper.writeValueAsString(questions);
    }

    private String getServiceUrl(String serviceName) {
        return "http://" + serviceName + "/api/questions";
    }

}
