package com.apexdigital.kafka;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KafkaMessageProducer {

    private static final Logger logger = LogManager.getLogger(KafkaMessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> sendMessage(String topic, String message) {
        logger.info("Message sent to Kafka");
        return Mono.fromFuture(kafkaTemplate.send(topic, message)).then();
    }

//    public Mono<Void> sendMessage(String topic, String message) {
//        return Mono.fromRunnable(() -> {
//            try {
//                kafkaTemplate.send(topic, message);
//                logger.info("Message sent to Kafka: {}", message);
//            } catch (Exception e) {
//                logger.error("Error sending message to Kafka", e);
//            }
//        });
//    }
}