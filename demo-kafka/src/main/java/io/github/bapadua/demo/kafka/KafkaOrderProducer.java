package io.github.bapadua.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static io.github.bapadua.demo.model.constants.Topics.CREATE_ORDER_TOPIC;

@Slf4j
@Service
public class KafkaOrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaOrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(CREATE_ORDER_TOPIC, "key-test", message);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable throwable) {
                log.error("failed to send, errorMessage = {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("successfuly sent message={} with offset=[{}]", message, result.getRecordMetadata().offset());
            }
        });
    }
}
