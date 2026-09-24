package io.harness.lab.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventory;

    @BeforeEach
    void setUp() {
        inventory = new InventoryService();
        inventory.addStock("SKU-001", 50);
        inventory.addStock("SKU-002", 5);
    }

    @Test
    void checkAvailability_sufficient() {
        assertTrue(inventory.isAvailable("SKU-001", 10));
    }

    @Test
    void checkAvailability_insufficient() {
        assertFalse(inventory.isAvailable("SKU-002", 10));
    }

    @Test
    void checkAvailability_unknownSku() {
        assertFalse(inventory.isAvailable("SKU-999", 1));
    }

    @Test
    void reserve_deductsStock() {
        assertEquals(10, inventory.reserve("SKU-001", 10));
        assertEquals(40, inventory.getStock("SKU-001"));
    }

    @Test
    void reserve_insufficientReturnsZero() {
        assertEquals(0, inventory.reserve("SKU-002", 100));
        assertEquals(5, inventory.getStock("SKU-002"));
    }

    @Test
    void lowStockItems_belowThreshold() {
        var low = inventory.lowStockItems(10);
        assertTrue(low.containsKey("SKU-002"));
        assertFalse(low.containsKey("SKU-001"));
    }

    @Test
    void addStock_accumulates() {
        inventory.addStock("SKU-001", 25);
        assertEquals(75, inventory.getStock("SKU-001"));
    }
}
