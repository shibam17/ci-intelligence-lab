package io.harness.lab.order;

import java.math.BigDecimal;

public record OrderItem(String sku, String name, BigDecimal price, int quantity) {

    public OrderItem {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        if (price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price cannot be negative");
    }
}
