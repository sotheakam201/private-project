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
@Table(name = "tb_sub_menus")
@Entity
public class SubMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "menu_id",nullable = false)
    private Menu menu;

    @Column(name = "sub_menu_name_en")
    private String subMenuNameEn;

    @Column(name = "sub_menu_name_kh")
    private String subMenuNameKh;

    @OneToMany(mappedBy = "subMenu")
    private List<Permission> permissions;

}
