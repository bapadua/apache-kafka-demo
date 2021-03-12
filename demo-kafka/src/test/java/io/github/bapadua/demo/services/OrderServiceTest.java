package io.github.bapadua.demo.services;

import io.github.bapadua.demo.kafka.KafkaOrderProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.github.bapadua.demo.scenarios.ScenarioFactory.NEW_VALID_ORDER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    KafkaOrderProducer kafkaOrderProducer;

    @InjectMocks
    OrderService orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSendMessage() {
        orderService.createOrder(NEW_VALID_ORDER);
        verify(kafkaOrderProducer, atLeastOnce()).send(any(String.class));
    }
}