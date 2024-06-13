package com.apexdigital.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Value("${spring.kafka.consumer.concurrency}")
    private Integer concurrency;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private Integer maxPollRecords;

    @Value("${spring.kafka.consumer.max-poll-interval-ms}")
    private Integer maxPollInterval;

    @Value("${spring.kafka.consumer.poll-timeout}")
    private Integer pollTimeout;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private Boolean autoCommit;

    @Value("${spring.kafka.properties.consumer.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.properties.consumer.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.properties.consumer.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.consumer.schema-registry-url}")
    private String schemaRegistryUrl;

    @Value("${spring.kafka.consumer.value-converter-schema}")
    private String valueConverterSchema;

    @Value("${spring.kafka.consumer.ssl-endpoint-algorithm}")
    private String sslEndpointAlgorithm;

    @Value("${spring.kafka.consumer.schema-register-basic-auth-user-info}")
    private String schemaRegisterBasicAuthUserInfo;

    @Value("${spring.kafka.consumer.basic-auth-credentials}")
    private String basicAuthCredentials;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        configProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        configProps.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPollInterval);

        configProps.put("schema.registry.url", schemaRegistryUrl);
        configProps.put("value.converter.schema.registry.url", valueConverterSchema);
        configProps.put("ssl.endpoint.identification.algorithm", sslEndpointAlgorithm);
        configProps.put("schema.registry.basic.auth.user.info", schemaRegisterBasicAuthUserInfo);
        configProps.put("basic.auth.credentials.source", basicAuthCredentials);

        configProps.put("security.protocol", securityProtocol);
        configProps.put("sasl.mechanism", saslMechanism);
        configProps.put("sasl.jaas.config", saslJaasConfig);
        return configProps;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency); // número de hilos concurrentes para manejar alta demanda
        factory.getContainerProperties().setPollTimeout(pollTimeout);
        return factory;
    }
}
