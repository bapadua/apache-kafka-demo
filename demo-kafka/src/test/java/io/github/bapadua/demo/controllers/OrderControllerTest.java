package io.github.bapadua.demo.controllers;

import io.github.bapadua.demo.services.OrderService;
import io.github.bapadua.demo.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static io.github.bapadua.demo.scenarios.ScenarioFactory.NEW_VALID_ORDER;
import static io.github.bapadua.demo.scenarios.ScenarioFactory.ORDER_WITH_INVALID_CPF;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @MockBean
    OrderService orderService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void send_Valid_Message_Ok() throws Exception {

        doNothing().when(orderService).createOrder(NEW_VALID_ORDER);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(NEW_VALID_ORDER));

        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    public void send_InValid_CPF_BadRequest() throws Exception {

        doNothing().when(orderService).createOrder(ORDER_WITH_INVALID_CPF);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(ORDER_WITH_INVALID_CPF));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }
}