package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QOrderItemDto is a Querydsl Projection type for OrderItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderItemDto extends ConstructorExpression<OrderItemDto> {

    private static final long serialVersionUID = -547050976L;

    public QOrderItemDto(com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDateTime, com.querydsl.core.types.Expression<com.hyunhii.dinnerForU.entity.PayMethod> method, com.querydsl.core.types.Expression<java.time.LocalDate> subscribeDate, com.querydsl.core.types.Expression<Integer> cnt, com.querydsl.core.types.Expression<Integer> price) {
        super(OrderItemDto.class, new Class<?>[]{java.time.LocalDateTime.class, com.hyunhii.dinnerForU.entity.PayMethod.class, java.time.LocalDate.class, int.class, int.class}, orderDateTime, method, subscribeDate, cnt, price);
    }

}

