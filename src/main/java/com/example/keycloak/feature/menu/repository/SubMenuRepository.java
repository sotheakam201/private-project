package com.example.keycloak.feature.menu.repository;

import com.example.keycloak.domain.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubMenuRepository extends JpaRepository<SubMenu,Integer> {
}
