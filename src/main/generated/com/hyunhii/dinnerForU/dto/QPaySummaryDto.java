package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QPaySummaryDto is a Querydsl Projection type for PaySummaryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPaySummaryDto extends ConstructorExpression<PaySummaryDto> {

    private static final long serialVersionUID = 1647759327L;

    public QPaySummaryDto(com.querydsl.core.types.Expression<Long> orderId, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDateTime, com.querydsl.core.types.Expression<Integer> amountCnt, com.querydsl.core.types.Expression<Integer> amountPrice) {
        super(PaySummaryDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, int.class, int.class}, orderId, orderDateTime, amountCnt, amountPrice);
    }

}

