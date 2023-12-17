package com.example.examinator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
public class MainController {

    @Value("${health_check_message}")
    private String greetingsMessage;

    @GetMapping("health-check")
    private String healthCheck() {
        log.info(greetingsMessage);
        return greetingsMessage;
    }

}
