package com.example.keycloak.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_menus")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_name_en",nullable = false,unique = true,length = 100)
    private String menuNameEn;

    @Column(name = "menu_name_kh",unique = true,length = 100)
    private String menuNameKh;

    @Column(name = "icon")
    private String icon;

    @OneToMany(mappedBy = "menu")
    private List<SubMenu> subMenus;

}
