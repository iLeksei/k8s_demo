package com.example.java_questions_service.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @GetMapping(value = "health-check")
    public String healthCheck() {
        log.info("JS questions service is alive!");
        return "alive!";
    }

}
