package com.example.revenueservice.services;

import com.example.revenueservice.dto.Actor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActorConsumerService {

    @Autowired
    CounterService counterService;

    @Value("${app.topics.create-actor}")
    private String CREATE_ACTOR;


    @KafkaListener(topics = "ON_CREATE_ACTOR")
    public void consume(String message) {
        log.info("received message -> {}", message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Actor actor = objectMapper.readValue(message, Actor.class);
            counterService.updateCounter(actor);
        } catch (Exception e) {
            log.error("CAUGHT ERROR -> {}", e.getMessage());
        }
    }

}
