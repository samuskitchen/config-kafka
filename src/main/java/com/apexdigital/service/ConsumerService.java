package com.apexdigital.service;

import com.apexdigital.model.EndDateRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ConsumerService {

    private static final Logger logger = LogManager.getLogger(ConsumerService.class);

    public ConsumerService() {
        // document why this constructor is empty
    }

    public Mono<Void> processMessage(String topic, String message) {
        // Lógica para procesar el mensaje
        logger.info("Processing message from topic: {}", topic);
        logger.info("Message: {}", message);

        return Mono.empty();
    }
}
