package io.github.bapadua.demo.messaging;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static io.github.bapadua.demo.model.constants.Topics.CREATE_ORDER_TOPIC;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic bootstrapTopic() {
        return new NewTopic(CREATE_ORDER_TOPIC, 1, (short) 1);
    }
}
