package com.hyunhii.dinnerForU.dto;

import com.hyunhii.dinnerForU.entity.FoodType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FoodSearchCondition {
    private FoodType foodType;
}
