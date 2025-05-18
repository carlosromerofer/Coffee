package com.example.coffe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.coffe.service.CoffeeOrderService;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CoffeOrderController {
    private final CoffeeOrderService coffeeOrderService;

    public CoffeOrderController(CoffeeOrderService coffeeOrderService) {
        this.coffeeOrderService = coffeeOrderService;
    }

    @GetMapping("/loadedData")
    public Map<String, Object> getLoadedData() {
        return coffeeOrderService.getLoadedData();
    }

    @GetMapping("/amountPaid")
    public Map<String, Double> getAmountPaid() {
        return coffeeOrderService.calculateAmountPaid();
    }

    @GetMapping("/amountOwed")
    public Map<String, Double> getAmountOwed() {
        return coffeeOrderService.calculateAmountOwed();
    }

}
