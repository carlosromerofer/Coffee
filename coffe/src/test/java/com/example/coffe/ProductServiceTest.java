package com.example.coffe;

import org.junit.jupiter.api.Test;

import com.example.coffe.dto.Order;
import com.example.coffe.dto.Payment;
import com.example.coffe.dto.Product;
import com.example.coffe.service.CoffeeOrderService;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeOrderServiceTest {

    @Test
    void testCalculateAmountPaidAndOwed() {
        Product product = new Product("long black", Map.of("medium", 3.0, "large", 4.0));
        List<Product> products = List.of(product);

        Order order = new Order("alice", "long black", "medium");
        List<Order> orders = List.of(order);

        Payment payment = new Payment("alice", 2.0);
        List<Payment> payments = List.of(payment);

        CoffeeOrderService service = new CoffeeOrderService(products, orders, payments);

        Map<String, Double> amountPaid = service.calculateAmountPaid();
        assertEquals(2.0, amountPaid.get("alice"));

        Map<String, Double> amountOwed = service.calculateAmountOwed();
        assertEquals(1.0, amountOwed.get("alice")); // 3.0 (owed) - 2.0 (paid)
    }
}