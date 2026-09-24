package io.harness.lab.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PricingEngine {

    private static final Map<String, BigDecimal> TIER_DISCOUNTS = Map.of(
            "bronze", BigDecimal.valueOf(5),
            "silver", BigDecimal.valueOf(10),
            "gold", BigDecimal.valueOf(15),
            "platinum", BigDecimal.valueOf(20)
    );

    public BigDecimal applyTierDiscount(BigDecimal amount, String tier) {
        BigDecimal percent = TIER_DISCOUNTS.getOrDefault(tier.toLowerCase(), BigDecimal.ZERO);
        BigDecimal discount = amount.multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return amount.subtract(discount);
    }

    public BigDecimal calculateTax(BigDecimal amount, String region) {
        BigDecimal rate = switch (region.toLowerCase()) {
            case "us-ca" -> BigDecimal.valueOf(8.25);
            case "us-ny" -> BigDecimal.valueOf(8.875);
            case "us-tx" -> BigDecimal.valueOf(6.25);
            case "eu-de" -> BigDecimal.valueOf(19);
            case "eu-fr" -> BigDecimal.valueOf(20);
            default -> BigDecimal.ZERO;
        };
        return amount.multiply(rate).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal bulkDiscount(BigDecimal unitPrice, int quantity) {
        if (quantity >= 100) return unitPrice.multiply(BigDecimal.valueOf(0.70));
        if (quantity >= 50) return unitPrice.multiply(BigDecimal.valueOf(0.80));
        if (quantity >= 20) return unitPrice.multiply(BigDecimal.valueOf(0.90));
        return unitPrice;
    }
}
