package com.example.java_questions_service.controllers;

import com.example.java_questions_service.services.QuestionsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class QuestionsController {

    private final QuestionsService questionsService;

    @Autowired
    QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping("questions")
    public String getQuestions() throws JsonProcessingException {
        log.info("GET /api/questions");
        List<String> questionsList = questionsService.getQuestionsList();
        return new ObjectMapper().writeValueAsString(questionsList);
    }


}
