package com.example.coffe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.coffe.controller.CoffeOrderController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoffeOrderController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getProductReturnsProduct() throws Exception {
        mockMvc.perform(get("/products/long black/medium"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.drinkName").value("long black"))
                .andExpect(jsonPath("$.prices.medium").exists());
    }

    @Test
    void getProductReturnsErrorForInvalid() throws Exception {
        mockMvc.perform(get("/products/invalid/medium"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Invalid drink or size: invalid, medium"));
    }
}