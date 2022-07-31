package com.university.users.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.users.entities.Actor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ActorProducerService {

    @Value("${app.topics.create-actor}")
    private String CREATE_ACTOR_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Actor actor) {
        // convert actor to json string
        ObjectMapper objMapper = new ObjectMapper();

        try {
            String actorJson = objMapper.writeValueAsString(actor);
            log.info("Publishing to topic {} {}", CREATE_ACTOR_TOPIC, actorJson);
            this.kafkaTemplate.send(CREATE_ACTOR_TOPIC, actorJson);
        } catch (IOException ioException) {
            log.error("Error while serializing json {}", ioException.getMessage());
        } catch (Exception exception) {
            log.error("Error publishing message");
        }
    }
}
