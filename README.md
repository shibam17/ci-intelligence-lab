# ci-intelligence-lab

Java 17 / Maven project for validating Harness CI Intelligence features: Cache Intelligence, Test Intelligence, and Build Intelligence.

## Structure

```
src/main/java/io/harness/lab/
  order/          Order processing and totals
  inventory/      Stock management and reservation
  pricing/        Tier discounts, tax, bulk pricing
  notification/   Order and shipment notifications
  validation/     Input sanitisation and format checks
```

## Run locally

```bash
mvn clean test
```

Requires Java 17+.
