package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QMenuDto is a Querydsl Projection type for MenuDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMenuDto extends ConstructorExpression<MenuDto> {

    private static final long serialVersionUID = 1725916990L;

    public QMenuDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<java.time.LocalDate> date, com.querydsl.core.types.Expression<Integer> stockQuantity, com.querydsl.core.types.Expression<Integer> price) {
        super(MenuDto.class, new Class<?>[]{long.class, java.time.LocalDate.class, int.class, int.class}, id, date, stockQuantity, price);
    }

    public QMenuDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<java.time.LocalDate> date, com.querydsl.core.types.Expression<Integer> stockQuantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Long> foodId, com.querydsl.core.types.Expression<String> foodName) {
        super(MenuDto.class, new Class<?>[]{long.class, java.time.LocalDate.class, int.class, int.class, long.class, String.class}, id, date, stockQuantity, price, foodId, foodName);
    }

    public QMenuDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<java.time.LocalDate> date, com.querydsl.core.types.Expression<Integer> stockQuantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Long> foodId, com.querydsl.core.types.Expression<String> foodName, com.querydsl.core.types.Expression<String> foodImg) {
        super(MenuDto.class, new Class<?>[]{long.class, java.time.LocalDate.class, int.class, int.class, long.class, String.class, String.class}, id, date, stockQuantity, price, foodId, foodName, foodImg);
    }

}

