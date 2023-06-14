package com.example.examinator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.DiscoveryClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@RestController
public class QuestionsController {

    @Value("${js_questions_service_name}")
    private String jsQuestionsServiceName;

    @Value("${java_questions_service_name}")
    private String javaQuestionsServiceName;

    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper;


    @Autowired
    public QuestionsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        this.objectMapper = new ObjectMapper();
    }


    @GetMapping("questions")
    public String getQuestions() {
        log.info("GET /questions");

        log.info("Return questions list: {}", "");
        return "";
    }


    @GetMapping("questions-async")
    public String getQuestionsAsync1() throws JsonProcessingException {
        log.info("GET /api/questions");
        CompletableFuture<List<String>> jsQuestions = CompletableFuture.supplyAsync(() -> {
            try {
                String serviceUrl = getServiceUrl(jsQuestionsServiceName);
                log.info("GET js questions list from URL: {}", serviceUrl);
                String responseJson =  restTemplate.getForObject(getServiceUrl(jsQuestionsServiceName), String.class);
                return objectMapper.readValue(responseJson, new TypeReference<>() {});
            } catch (Exception ex) {
                log.error("Error with getting JS questions list");
                return Collections.emptyList();
            }
        });
        CompletableFuture<List<String>> javaQuestions = CompletableFuture.supplyAsync(() -> {
            try {
                String serviceUrl = getServiceUrl(javaQuestionsServiceName);
                log.info("GET java questions list from URL: {}", serviceUrl);
                String responseJson =  restTemplate.getForObject(getServiceUrl(javaQuestionsServiceName), String.class);
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
