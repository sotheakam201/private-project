package com.example.keycloak.feature.menu.dto;

public record MenuRequest(
        String menu,
        String subMenu,
        String permission
) {
}
