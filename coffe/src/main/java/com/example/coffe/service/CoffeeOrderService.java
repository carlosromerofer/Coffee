package com.example.coffe.service;

import org.springframework.stereotype.Service;

import com.example.coffe.dto.Order;
import com.example.coffe.dto.Payment;
import com.example.coffe.dto.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoffeeOrderService {

    private final List<Product> products;
    private final List<Order> orders;
    private final List<Payment> payments;

    public CoffeeOrderService(List<Product> products, List<Order> orders, List<Payment> payments) {
        this.products = products;
        this.orders = orders;
        this.payments = payments;
    }

    public Map<String, Object> getLoadedData() {
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);
        data.put("orders", orders);
        data.put("payments", payments);
        return data;
    }

    /**
     * Calculate the total amount paid by each user.
     *
     * @return A map of user names to the total amount paid.
     */
    public Map<String, Double> calculateAmountPaid() {
        Map<String, Double> amountPaid = new HashMap<>();

        for (Payment payment : payments) {
            String user = payment.getUser();
            double amount = payment.getAmount();
            amountPaid.put(user, amountPaid.getOrDefault(user, 0.0) + amount);
        }

        return amountPaid;
    }

    /**
     * Calculate the total amount owed by each user.
     *
     * @return A map of user names to the amount owed.
     */
    public Map<String, Double> calculateAmountOwed() {
        Map<String, Double> amountPaid = calculateAmountPaid();
        Map<String, Double> amountOwed = new HashMap<>();

        for (Order order : orders) {
            String user = order.getUser();
            double orderPrice = getOrderPrice(order.getDrink(), order.getSize());

            amountOwed.put(user, amountOwed.getOrDefault(user, 0.0) + orderPrice);
        }

        for (String user : amountOwed.keySet()) {
            double totalOwed = amountOwed.get(user);
            double totalPaid = amountPaid.getOrDefault(user, 0.0);
            amountOwed.put(user, totalOwed - totalPaid);
        }

        return amountOwed;
    }

    /**
     * Get the price of a drink based on its size.
     *
     * @param drink The drink name.
     * @param size  The drink size.
     * @return The price of the drink.
     * @throws IllegalArgumentException if the drink or size is not found.
     */
    private double getOrderPrice(String drink, String size) {
        String normalizedDrink = drink.trim().toLowerCase();
        String normalizedSize = size.trim().toLowerCase();
        for (Product product : products) {
            String productDrinkName = product.getDrinkName();
            if (productDrinkName != null && productDrinkName.trim().toLowerCase().equals(normalizedDrink)) {
                Double price = product.getPrices().get(normalizedSize);
                if (price != null) {
                    return price;
                }
            }
        }
        throw new IllegalArgumentException("Invalid drink or size: " + drink + ", " + size);
    }
}
