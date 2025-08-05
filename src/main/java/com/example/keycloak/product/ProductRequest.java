package com.example.keycloak.product;

import java.math.BigDecimal;

public record ProductRequest(
        String productName,
        String desc,
        BigDecimal price
) {
}
