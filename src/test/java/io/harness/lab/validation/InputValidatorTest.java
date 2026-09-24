package io.harness.lab.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    private final InputValidator validator = new InputValidator();

    @Test
    void validEmail() {
        assertTrue(validator.isValidEmail("user@example.com"));
    }

    @Test
    void invalidEmail_noAt() {
        assertFalse(validator.isValidEmail("userexample.com"));
    }

    @Test
    void invalidEmail_null() {
        assertFalse(validator.isValidEmail(null));
    }

    @Test
    void validSku() {
        assertTrue(validator.isValidSku("AB-1234"));
    }

    @Test
    void invalidSku_lowercase() {
        assertFalse(validator.isValidSku("ab-1234"));
    }

    @Test
    void validQuantity() {
        assertTrue(validator.isValidQuantity(1));
        assertTrue(validator.isValidQuantity(10_000));
    }

    @Test
    void invalidQuantity_zero() {
        assertFalse(validator.isValidQuantity(0));
    }

    @Test
    void invalidQuantity_tooHigh() {
        assertFalse(validator.isValidQuantity(10_001));
    }

    @Test
    void sanitise_stripsHtml() {
        assertEquals("hello world", validator.sanitise("<b>hello</b> world"));
    }

    @Test
    void sanitise_null() {
        assertEquals("", validator.sanitise(null));
    }

    @Test
    void validRegion() {
        assertTrue(validator.isValidRegion("us-ca"));
    }

    @Test
    void invalidRegion_uppercase() {
        assertFalse(validator.isValidRegion("US-CA"));
    }
}
