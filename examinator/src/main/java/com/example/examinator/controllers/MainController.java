package com.example.examinator.controllers;

import com.example.examinator.services.MainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    private final MainServiceImpl mainService;

    @Autowired
    public MainController(MainServiceImpl mainService) {
        this.mainService = mainService;
    }


    @GetMapping("health-check")
    private String healthCheck() {
        log.info("GET health-check");
        return mainService.healthCheck();
    }

}
