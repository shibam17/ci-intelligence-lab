package io.harness.lab.notification;

import java.time.LocalDateTime;

public record Notification(String type, String referenceId, String recipient, String message, LocalDateTime sentAt) {
}
