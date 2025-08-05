package com.example.keycloak.feature.menu.repository;

import com.example.keycloak.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
