package com.example.examinator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QuestionsController {

    @Value("${js_questions_service_url}")
    private String jsQuestionsServiceUrl;

    @Value("${java_questions_service_url}")
    private String javaQuestionsServiceUrl;


    @GetMapping("questions")
    public String getQuestions() {
        log.info("GET /questions");


        log.info("Return questions list: {}", "");
        return "";
    }

}
