package com.apexdigital.listener;

import com.apexdigital.service.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KafkaEventListener {

    private static final Logger logger = LogManager.getLogger(KafkaEventListener.class);
    private final ConsumerService kafkaConsumer;

    @Value("${topics.sender.closed-day-route-created}")
    private String kafkaTopic;

    @Autowired
    public KafkaEventListener(ConsumerService kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @KafkaListener(topics = "${topics.sender.closed-day-route-created}", groupId = "${spring.kafka.consumer.group-id}")
    public Mono<Void> listen(ConsumerRecord<String, String> messages) {
        // Procesa el mensaje recibido de Kafka usando el servicio de consumidor
        return kafkaConsumer.processMessage(kafkaTopic, messages.value());
    }
}
