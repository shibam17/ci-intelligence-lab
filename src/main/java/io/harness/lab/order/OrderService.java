package io.harness.lab.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private final List<OrderItem> items;
    private final String orderId;
    private final LocalDateTime createdAt;

    public OrderService(List<OrderItem> items) {
        this.items = items;
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.createdAt = LocalDateTime.now();
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal applyDiscount(BigDecimal discountPercent) {
        BigDecimal total = calculateTotal();
        BigDecimal discount = total.multiply(discountPercent).divide(BigDecimal.valueOf(100));
        return total.subtract(discount);
    }

    public boolean isEligibleForFreeShipping() {
        return calculateTotal().compareTo(BigDecimal.valueOf(50)) > 0;
    }

    public int totalItemCount() {
        return items.stream().mapToInt(OrderItem::quantity).sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }
}
