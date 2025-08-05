package com.example.keycloak.endpoint_assess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class EndpointAccess {
    private String method;
    private String pattern;
    private String roles;
}
