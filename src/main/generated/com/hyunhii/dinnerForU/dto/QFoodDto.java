package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QFoodDto is a Querydsl Projection type for FoodDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QFoodDto extends ConstructorExpression<FoodDto> {

    private static final long serialVersionUID = 95067103L;

    public QFoodDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<com.hyunhii.dinnerForU.entity.FoodType> type, com.querydsl.core.types.Expression<String> useYN) {
        super(FoodDto.class, new Class<?>[]{long.class, String.class, com.hyunhii.dinnerForU.entity.FoodType.class, String.class}, id, name, type, useYN);
    }

    public QFoodDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<com.hyunhii.dinnerForU.entity.FoodType> type, com.querydsl.core.types.Expression<String> foodImg, com.querydsl.core.types.Expression<String> useYN) {
        super(FoodDto.class, new Class<?>[]{long.class, String.class, com.hyunhii.dinnerForU.entity.FoodType.class, String.class, String.class}, id, name, type, foodImg, useYN);
    }

}

