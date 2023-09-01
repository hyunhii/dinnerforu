package com.hyunhii.dinnerForU.dto;

import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.entity.FoodType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FoodDto {

    private Long foodId;
    private String foodName;
    private FoodType foodType;
    private String useYN;
    private String foodImg;

    @QueryProjection
    public FoodDto(Long id, String name, FoodType type, String useYN) {
        this.foodId = id;
        this.foodName = name;
        this.foodType =type;
        this.useYN = useYN;
    }


    @QueryProjection
    public FoodDto(Long id, String name, FoodType type, String foodImg, String useYN) {
        this.foodId = id;
        this.foodName = name;
        this.foodType =type;
        this.foodImg = foodImg;
        this.useYN = useYN;
    }
}
