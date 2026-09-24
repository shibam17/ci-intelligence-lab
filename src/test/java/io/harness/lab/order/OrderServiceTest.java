package io.harness.lab.order;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void calculateTotal_singleItem() {
        var order = new OrderService(List.of(new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(10), 3)));
        assertEquals(0, BigDecimal.valueOf(30).compareTo(order.calculateTotal()));
    }

    @Test
    void calculateTotal_multipleItems() {
        var order = new OrderService(List.of(
                new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(10), 2),
                new OrderItem("SKU-002", "Gadget", BigDecimal.valueOf(25), 1)
        ));
        assertEquals(0, BigDecimal.valueOf(45).compareTo(order.calculateTotal()));
    }

    @Test
    void applyDiscount_tenPercent() {
        var order = new OrderService(List.of(new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(100), 1)));
        assertEquals(0, BigDecimal.valueOf(90).compareTo(order.applyDiscount(BigDecimal.TEN)));
    }

    @Test
    void freeShipping_aboveThreshold() {
        var order = new OrderService(List.of(new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(51), 1)));
        assertTrue(order.isEligibleForFreeShipping());
    }

    @Test
    void freeShipping_belowThreshold() {
        var order = new OrderService(List.of(new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(49), 1)));
        assertFalse(order.isEligibleForFreeShipping());
    }

    @Test
    void totalItemCount() {
        var order = new OrderService(List.of(
                new OrderItem("SKU-001", "Widget", BigDecimal.TEN, 3),
                new OrderItem("SKU-002", "Gadget", BigDecimal.TEN, 7)
        ));
        assertEquals(10, order.totalItemCount());
    }

    @Test
    void orderIdGenerated() {
        var order = new OrderService(List.of(new OrderItem("SKU-001", "Widget", BigDecimal.TEN, 1)));
        assertNotNull(order.getOrderId());
        assertEquals(8, order.getOrderId().length());
    }
}
