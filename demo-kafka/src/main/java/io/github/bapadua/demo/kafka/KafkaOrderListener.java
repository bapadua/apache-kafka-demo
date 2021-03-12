package io.github.bapadua.demo.kafka;

import io.github.bapadua.demo.model.constants.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaOrderListener {

    @KafkaListener(topics = Topics.CREATE_ORDER_TOPIC, groupId = "defaultGroup")
    public void orderListener(final String message) {
        log.info("-----------------------------------------");
        log.info("New Order to {}", message);
        log.info("-----------------------------------------");
    }
}
