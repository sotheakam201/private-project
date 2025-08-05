package com.example.keycloak.me;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/me")
public class MeController {
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    @GetMapping
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> response = new HashMap<>();
        response.put("username", jwt.getClaimAsString("preferred_username"));

        List<String> roles = extractRoles(jwt);
        response.put("roles", roles);
        response.put("permissions", roles); // optional: same if roles = permissions

        return response;
    }

    private List<String> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null || resourceAccess.get(resourceId) == null) return List.of();

        Map<String, Object> client = (Map<String, Object>) resourceAccess.get(resourceId);
        return (List<String>) client.get("roles");
    }
}
