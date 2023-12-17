package com.example.examinator.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RefreshScope
@Slf4j
@Service
public class MainServiceImpl implements MainService {


    @Value("${health_check_message}")
    private String greetingsMessage;

    @Override
    public String healthCheck() {
        log.info("MainServiceImpl::healthCheck...");
        return Objects.requireNonNullElse(greetingsMessage, "It's default health-check message!");
    }
}
