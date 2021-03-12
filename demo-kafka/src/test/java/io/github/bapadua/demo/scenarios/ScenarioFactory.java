package io.github.bapadua.demo.scenarios;

import io.github.bapadua.demo.entity.Order;

import java.util.UUID;

public class ScenarioFactory {

    public static final Order NEW_VALID_ORDER = getValidOrder();
    public static final Order ORDER_WITH_INVALID_CPF = getInvalidCpfOrder();
    public static final String VALID_CPF = "46473139052";
    public static final String INVALID_CPF = "1111111111";
    public static final long DEFAULT_ID = 1L;

    private static Order getValidOrder() {
        return Order.builder()
                .id(DEFAULT_ID)
                .orderId(UUID.randomUUID().toString())
                .federalDocument(VALID_CPF)
                .build();
    }


    private static Order getInvalidCpfOrder() {
        return Order.builder()
                .id(DEFAULT_ID)
                .orderId(UUID.randomUUID().toString())
                .federalDocument(INVALID_CPF)
                .build();
    }
}
