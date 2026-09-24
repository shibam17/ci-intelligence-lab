package io.harness.lab.pricing;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class PricingEngineTest {

    private final PricingEngine engine = new PricingEngine();

    @Test
    void tierDiscount_gold() {
        BigDecimal result = engine.applyTierDiscount(BigDecimal.valueOf(100), "gold");
        assertEquals(0, BigDecimal.valueOf(85).compareTo(result));
    }

    @Test
    void tierDiscount_unknownTier() {
        BigDecimal result = engine.applyTierDiscount(BigDecimal.valueOf(100), "diamond");
        assertEquals(0, BigDecimal.valueOf(100).compareTo(result));
    }

    @Test
    void tax_california() {
        BigDecimal tax = engine.calculateTax(BigDecimal.valueOf(100), "us-ca");
        assertEquals(0, BigDecimal.valueOf(8.25).compareTo(tax));
    }

    @Test
    void tax_germany() {
        BigDecimal tax = engine.calculateTax(BigDecimal.valueOf(100), "eu-de");
        assertEquals(0, BigDecimal.valueOf(19).compareTo(tax));
    }

    @Test
    void tax_unknownRegion() {
        BigDecimal tax = engine.calculateTax(BigDecimal.valueOf(100), "xx-yy");
        assertEquals(0, BigDecimal.ZERO.compareTo(tax));
    }

    @Test
    void bulkDiscount_under20() {
        assertEquals(0, BigDecimal.TEN.compareTo(engine.bulkDiscount(BigDecimal.TEN, 5)));
    }

    @Test
    void bulkDiscount_50units() {
        assertEquals(0, BigDecimal.valueOf(8.0).compareTo(engine.bulkDiscount(BigDecimal.TEN, 50)));
    }

    @Test
    void bulkDiscount_100units() {
        assertEquals(0, BigDecimal.valueOf(7.0).compareTo(engine.bulkDiscount(BigDecimal.TEN, 100)));
    }
}
