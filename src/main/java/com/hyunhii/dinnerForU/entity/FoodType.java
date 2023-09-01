package com.hyunhii.dinnerForU.entity;

import lombok.Getter;

@Getter
public enum FoodType {
    MAIN("메인"), DESSERT("디저트");

    private String name;

    FoodType(String name) {
        this.name = name;
    }
}
