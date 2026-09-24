package io.harness.lab.validation;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern EMAIL = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern SKU = Pattern.compile("^[A-Z]{2,4}-\\d{4,8}$");

    public boolean isValidEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    public boolean isValidSku(String sku) {
        return sku != null && SKU.matcher(sku).matches();
    }

    public boolean isValidQuantity(int qty) {
        return qty > 0 && qty <= 10_000;
    }

    public String sanitise(String input) {
        if (input == null) return "";
        return input.replaceAll("<[^>]*>", "").replaceAll("[\"'&]", "").trim();
    }

    public boolean isValidRegion(String region) {
        return region != null && region.matches("^[a-z]{2}-[a-z]{2}$");
    }
}
