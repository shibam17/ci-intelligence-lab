package io.harness.lab.notification;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private final NotificationService service = new NotificationService();

    @Test
    void orderConfirmation_createsNotification() {
        var n = service.orderConfirmation("ORD-123", "user@example.com");
        assertEquals("ORDER_CONFIRM", n.type());
        assertEquals("ORD-123", n.referenceId());
    }

    @Test
    void shipmentUpdate_includesTracking() {
        var n = service.shipmentUpdate("ORD-123", "user@example.com", "TRK-456");
        assertTrue(n.message().contains("TRK-456"));
    }

    @Test
    void lowStockAlert_sendsToOps() {
        var n = service.lowStockAlert("SKU-001", 3);
        assertEquals("ops@internal", n.recipient());
    }

    @Test
    void countByType_filtersCorrectly() {
        service.orderConfirmation("ORD-1", "a@b.com");
        service.orderConfirmation("ORD-2", "c@d.com");
        service.shipmentUpdate("ORD-1", "a@b.com", "TRK-1");
        assertEquals(2, service.countByType("ORDER_CONFIRM"));
        assertEquals(1, service.countByType("SHIPMENT"));
    }

    @Test
    void sentNotifications_returnsImmutableCopy() {
        service.orderConfirmation("ORD-1", "a@b.com");
        var list = service.getSentNotifications();
        assertThrows(UnsupportedOperationException.class, () -> list.add(null));
    }
}
