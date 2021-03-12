package io.github.bapadua.demo.services;

import io.github.bapadua.demo.converter.MessageConverter;
import io.github.bapadua.demo.entity.Order;
import io.github.bapadua.demo.kafka.KafkaOrderProducer;
import io.github.bapadua.demo.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaOrderProducer orderProducer;
    private final MessageConverter converter;

    public OrderService(OrderRepository orderRepository, KafkaOrderProducer orderProducer, MessageConverter converter) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.converter = converter;
    }

    public Order createOrder(final Order order) {
        return process(order);
    }

    private Order send(final Order order) {
        orderProducer.send(getMessage(order));
        return order;
    }

    private Order persist(final Order order) {
        return send(orderRepository.save(order));
    }

    private Order process(final Order order) {
        return persist(Order.builder()
                .orderId(UUID.randomUUID().toString())
                .fullName(order.getFullName())
                .federalDocument(order.getFederalDocument())
                .build());
    }

    private String getMessage(final Order order) {
        return converter.convert(order);
    }
}
