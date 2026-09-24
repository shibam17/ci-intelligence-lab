package io.harness.lab.order;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void validItem() {
        var item = new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(9.99), 2);
        assertEquals("SKU-001", item.sku());
        assertEquals(2, item.quantity());
    }

    @Test
    void rejectsZeroQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderItem("SKU-001", "Widget", BigDecimal.TEN, 0));
    }

    @Test
    void rejectsNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderItem("SKU-001", "Widget", BigDecimal.valueOf(-1), 1));
    }
}
