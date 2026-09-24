package io.harness.lab.inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    public void addStock(String sku, int quantity) {
        stock.merge(sku, quantity, Integer::sum);
    }

    public boolean isAvailable(String sku, int requested) {
        return stock.getOrDefault(sku, 0) >= requested;
    }

    public int reserve(String sku, int quantity) {
        int available = stock.getOrDefault(sku, 0);
        if (available < quantity) return 0;
        stock.put(sku, available - quantity);
        return quantity;
    }

    public int getStock(String sku) {
        return stock.getOrDefault(sku, 0);
    }

    public Map<String, Integer> lowStockItems(int threshold) {
        Map<String, Integer> low = new HashMap<>();
        stock.forEach((sku, qty) -> {
            if (qty <= threshold) low.put(sku, qty);
        });
        return low;
    }
}
