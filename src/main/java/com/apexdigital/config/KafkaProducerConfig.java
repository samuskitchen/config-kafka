package com.apexdigital.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.retries}")
    private Integer retries;

    @Value("${spring.kafka.producer.batch-size}")
    private Integer batchSize;

    @Value("${spring.kafka.producer.linger-ms}")
    private Integer lingerMs;

    @Value("${spring.kafka.producer.buffer-memory}")
    private Integer bufferMemory;

    @Value("${spring.kafka.properties.producer.security.protocol}")
    private String securityProtocol;

    @Value("${spring.kafka.properties.producer.sasl.mechanism}")
    private String saslMechanism;

    @Value("${spring.kafka.properties.producer.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value("${spring.kafka.producer.schema-registry-url}")
    private String schemaRegistryUrl;

    @Value("${spring.kafka.producer.value-converter-schema}")
    private String valueConverterSchema;

    @Value("${spring.kafka.producer.ssl-endpoint-algorithm}")
    private String sslEndpointAlgorithm;

    @Value("${spring.kafka.producer.schema-register-basic-auth-user-info}")
    private String schemaRegisterBasicAuthUserInfo;

    @Value("${spring.kafka.producer.basic-auth-credentials}")
    private String basicAuthCredentials;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);

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
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
