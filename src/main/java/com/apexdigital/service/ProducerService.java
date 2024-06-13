package com.apexdigital.service;

import com.apexdigital.kafka.KafkaMessageProducer;
import com.apexdigital.model.EndDateRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.apexdigital.util.JsonUtil.MAPPER;

@Service
public class ProducerService {

    private static final Logger logger = LogManager.getLogger(ProducerService.class);

    @Value("${topics.sender.closed-day-route-created}")
    private String kafkaTopic;
    private final KafkaMessageProducer kafkaMessageProducer;

    public ProducerService(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    public Mono<Void> produceMessage(EndDateRequest request) {
        try {
            return kafkaMessageProducer.sendMessage(kafkaTopic, MAPPER.writeValueAsString(request));
        } catch (Exception e) {
            logger.error("Error sending message to Kafka", e);
        }
        return Mono.empty();
    }
}
