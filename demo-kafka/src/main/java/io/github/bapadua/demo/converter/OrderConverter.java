package io.github.bapadua.demo.converter;

import io.github.bapadua.demo.entity.Order;
import io.github.bapadua.demo.model.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    public Order convert(final OrderDTO dto) {
        return Order.builder()
                .fullName(dto.getFullName())
                .federalDocument(dto.getFederalDocument())
                .build();
    }

    public OrderDTO convert(final Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .fullName(order.getFullName())
                .federalDocument(order.getFederalDocument())
                .build();
    }
}
