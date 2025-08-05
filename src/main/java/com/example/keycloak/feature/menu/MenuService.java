package com.example.keycloak.feature.menu;

import com.example.keycloak.feature.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public void create(){

    }

}
