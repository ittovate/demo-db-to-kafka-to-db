package com.ittovative.demodbtokafka.config;

import com.ittovative.demodbtokafka.entity.Student;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.ittovative.demodbtokafka.constant.KafkaConstant.BOOTSTRAP_SERVERS;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.ACKS;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.BATCH_SIZE;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.BUFFER_MEMORY;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.KEY_SERIALIZER;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.LINGER_MS;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.RETRIES;
import static com.ittovative.demodbtokafka.constant.ProducerConstant.VALUE_SERIALIZER;

/**
 * Configuration for the Kafka producer.
 */
@Configuration
public class KafkaProducerConfig {
    /**
     * Create and configure a Producer.
     *
     * @return the producer factory
     */
    @Bean
    public ProducerFactory<String, Student> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER);
        configProps.put(ProducerConfig.ACKS_CONFIG, ACKS);
        configProps.put(ProducerConfig.RETRIES_CONFIG, RETRIES);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, BATCH_SIZE);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, LINGER_MS);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, BUFFER_MEMORY);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * KafkaTemplate for student which is used to students to Kafka topic.
     *
     * @return the kafka template
     */
    @Bean
    public KafkaTemplate<String, Student> studentKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
