package com.example.coffe.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.example.coffe.dto.Order;
import com.example.coffe.dto.Payment;
import com.example.coffe.dto.Product;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    public List<Product> products() throws Exception {
        ClassPathResource resource = new ClassPathResource("products.json");
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
            Type type = new com.google.gson.reflect.TypeToken<List<Product>>() {}.getType();
            return new com.google.gson.Gson().fromJson(reader, type);
        }
    }

    @Bean
    public List<Order> orders() throws Exception {
        ClassPathResource resource = new ClassPathResource("orders.json");
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
            Type type = new TypeToken<List<Order>>() {}.getType();
            return new Gson().fromJson(reader, type);
        }
    }

    @Bean
    public List<Payment> payments() throws Exception {
        ClassPathResource resource = new ClassPathResource("payments.json");
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
            Type type = new TypeToken<List<Payment>>() {}.getType();
            return new Gson().fromJson(reader, type);
        }
    }
}