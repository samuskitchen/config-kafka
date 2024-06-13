package com.apexdigital.controller;

import com.apexdigital.model.EndDateRequest;
import com.apexdigital.service.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class KafkaController {

    private final ProducerService producerService;

    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/publish")
    public Mono<Void> publishMessage(@RequestBody EndDateRequest request) {
        return producerService.produceMessage(request);
    }
}
