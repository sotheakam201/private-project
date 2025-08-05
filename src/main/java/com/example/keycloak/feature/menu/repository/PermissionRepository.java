package com.example.keycloak.feature.menu.repository;

import com.example.keycloak.domain.Permission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {

    @EntityGraph(attributePaths = "roles")
    List<Permission> findAll();
}
