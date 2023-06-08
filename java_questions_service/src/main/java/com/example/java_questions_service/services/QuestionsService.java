package com.example.java_questions_service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionsService {

    //todo should receive it from db
    public List<String> getQuestionsList() {
        log.info("Start to getting Java questions list");

        List<String> questionsList = new ArrayList<>();
        questionsList.add("Could you tell us something about LinkedList");
        questionsList.add("Could you tell us about Runtime Data Area in Java");

        log.info("Return Java questions list: {}", questionsList);
        return questionsList;
    }

}
