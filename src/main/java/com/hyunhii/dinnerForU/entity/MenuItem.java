package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuItem {

    @Id @GeneratedValue
    @Column(name = "menu_item_id")
    private Long id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public MenuItem(Food food) {
        this.food = food;
    }

    public MenuItem(Menu menu) {
        this.menu = menu;
    }

    public MenuItem(Food food, Menu menu) {
        this.food = food;
        this.menu = menu;
    }

    public void addMenu(Menu menu) {
        this.menu = menu;
    }
}
