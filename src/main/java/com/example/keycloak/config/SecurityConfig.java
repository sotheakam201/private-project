package com.example.keycloak.config;

import com.example.keycloak.domain.Permission;
import com.example.keycloak.domain.Role;
import com.example.keycloak.endpoint_assess.EndpointAccess;
import com.example.keycloak.feature.menu.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final PermissionRepository permissionRepository;
    private final JwtAuthConverter jwtAuthConverter;

//    List<EndpointAccess> accessList = List.of(
//            // Products
//            new EndpointAccess("GET", "/api/products/**", "admin,hr"),
//            new EndpointAccess("POST", "/api/products/**", "admin"),
//            new EndpointAccess("DELETE", "/api/products/**", "admin"),
//            new EndpointAccess("PUT", "/api/products/**", "admin"),
//
//            // Categories
//            new EndpointAccess("GET", "/api/categories/**", "admin,hr"),
//            new EndpointAccess("POST", "/api/categories/**", "admin,staff"),
//            new EndpointAccess("DELETE", "/api/categories/**", "admin"),
//            new EndpointAccess("PUT", "/api/categories/**", "admin")
//    );




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        List<EndpointAccess> accessList = permissionRepository.findAll().stream()
                .map(permission -> EndpointAccess.builder()
                        .method(permission.getStatusMethod())
                        .pattern(permission.getPattern())
                        .roles(permission.getRoles().stream()
                                .map(Role::getName)
                                .collect(Collectors.joining(",")))
                        .build())
                .toList();


        // 1. diable CSRF
        http.csrf(AbstractHttpConfigurer::disable);
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        // 2. Stateless session ( no cookie , only jwt )
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 3. Set Access Rules for URLs
        http.authorizeHttpRequests(auth -> {
            for (EndpointAccess access : accessList) {
                HttpMethod method = HttpMethod.valueOf(access.getMethod().toUpperCase());
                String[] roles = Arrays.stream(access.getRoles().split(","))
                        .map(String::trim)
                        .toArray(String[]::new);
                auth.requestMatchers(method, access.getPattern()).hasAnyRole(roles); // uses 'ROLE_' prefix automatically
            }
            auth.anyRequest().authenticated();
        });

        // 4. Enable JWT token
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
        );

        return http.build();
    }

}
