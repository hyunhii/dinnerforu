package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QOrderDto is a Querydsl Projection type for OrderDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderDto extends ConstructorExpression<OrderDto> {

    private static final long serialVersionUID = -492624845L;

    public QOrderDto(com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate) {
        super(OrderDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class}, orderId, orderDate);
    }

    public QOrderDto(com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<Integer> cnt, com.querydsl.core.types.Expression<String> foodName) {
        super(OrderDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, int.class, String.class}, orderId, orderDate, cnt, foodName);
    }

    public QOrderDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<java.time.LocalDate> subscribeDate, com.querydsl.core.types.Expression<Integer> cnt, com.querydsl.core.types.Expression<String> foodName) {
        super(OrderDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, java.time.LocalDate.class, int.class, String.class}, id, orderDate, subscribeDate, cnt, foodName);
    }

}

