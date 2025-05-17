package com.example.coffe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import com.example.coffe.dto.Product;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductValidationTest {

    @Test
    void allProductsHaveValidNamesAndSizes() throws Exception {
        try (InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/products.json"))) {
            Type type = new TypeToken<List<Product>>() {}.getType();
            List<Product> products = new Gson().fromJson(reader, type);
            for (Product product : products) {
                assertNotNull(product.getDrinkName());
                Map<String, Double> prices = product.getPrices();
                assertNotNull(prices);
                assertFalse(prices.isEmpty());
                for (Map.Entry<String, Double> entry : prices.entrySet()) {
                    assertNotNull(entry.getKey());
                    assertNotNull(entry.getValue());
                    assertTrue(entry.getValue() > 0);
                }
            }
        }
    }
}