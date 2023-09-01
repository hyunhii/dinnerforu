package com.hyunhii.dinnerForU.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Food {

    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    @NotNull
    private String foodName;
    @Enumerated(EnumType.STRING)
    private FoodType type;
    private String useYN;

    private String imgSrc;

    public Food(String foodName, FoodType type, String useYN) {
        this.foodName = foodName;
        this.type = type;
        this.useYN = useYN;
    }

    public Food(String foodName, FoodType type, String useYN, String imgSrc) {
        this.foodName = foodName;
        this.type = type;
        this.useYN = useYN;
        this.imgSrc = imgSrc;
    }

    public void changeFood(String foodName, FoodType type, String useYN) {
        this.foodName = foodName;
        if(this.type != type && type != null) {
            this.type = type;
        }
        this.useYN = useYN;
    }
}
