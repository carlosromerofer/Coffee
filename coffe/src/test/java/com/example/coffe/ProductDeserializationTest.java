package com.example.coffe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import com.example.coffe.dto.Product;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductDeserializationTest {

    @Test
    void productsJsonDeserializesCorrectly() throws Exception {
        try (InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/products.json"))) {
            Type type = new TypeToken<List<Product>>() {}.getType();
            List<Product> products = new Gson().fromJson(reader, type);
            assertFalse(products.isEmpty());
            assertNotNull(products.get(0).getDrinkName());
        }
    }
}