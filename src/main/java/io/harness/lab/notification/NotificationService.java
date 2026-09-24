package io.harness.lab.notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private final List<Notification> sent = new ArrayList<>();

    public Notification orderConfirmation(String orderId, String email) {
        var n = new Notification("ORDER_CONFIRM", orderId, email, "Order " + orderId + " confirmed", LocalDateTime.now());
        sent.add(n);
        return n;
    }

    public Notification shipmentUpdate(String orderId, String email, String trackingId) {
        var n = new Notification("SHIPMENT", orderId, email, "Shipped: " + trackingId, LocalDateTime.now());
        sent.add(n);
        return n;
    }

    public Notification lowStockAlert(String sku, int remaining) {
        var n = new Notification("LOW_STOCK", sku, "ops@internal", "Low stock: " + sku + " (" + remaining + " left)", LocalDateTime.now());
        sent.add(n);
        return n;
    }

    public List<Notification> getSentNotifications() {
        return List.copyOf(sent);
    }

    public long countByType(String type) {
        return sent.stream().filter(n -> n.type().equals(type)).count();
    }
}
