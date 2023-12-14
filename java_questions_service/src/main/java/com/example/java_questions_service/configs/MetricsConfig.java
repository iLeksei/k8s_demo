package com.example.java_questions_service.configs;

import com.example.java_questions_service.services.QuestionsService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public MeterBinder availableJavaQuestionsMetric(QuestionsService questionsService) {
        return registry -> {
            Gauge.builder("javaQuestions.max.number", () -> questionsService.getQuestionsList().size())
                    .baseUnit("NUMBER")
                    .description("Amount of available java questions")
                    .register(registry);
        };
    }

}
