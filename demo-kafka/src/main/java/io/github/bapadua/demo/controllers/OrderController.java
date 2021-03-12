package io.github.bapadua.demo.controllers;

import io.github.bapadua.demo.converter.OrderConverter;
import io.github.bapadua.demo.entity.Order;
import io.github.bapadua.demo.model.OrderDTO;
import io.github.bapadua.demo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter converter;

    public OrderController(OrderService orderService, OrderConverter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(final @Valid @RequestBody OrderDTO order) {
        return ResponseEntity.ok(converter.convert(
                orderService.createOrder(converter.convert(order)))
        );
    }
}
