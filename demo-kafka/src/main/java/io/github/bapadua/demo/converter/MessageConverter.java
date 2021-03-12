package io.github.bapadua.demo.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bapadua.demo.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String convert(final Order order) {
        String s;
        try {
            s = objectMapper.writeValueAsString(order);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
