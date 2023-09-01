package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hyunhii.dinnerForU.dto.QAddressDto is a Querydsl Projection type for AddressDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAddressDto extends ConstructorExpression<AddressDto> {

    private static final long serialVersionUID = -356520627L;

    public QAddressDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> addressName, com.querydsl.core.types.Expression<String> receiver, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> address1, com.querydsl.core.types.Expression<String> address2, com.querydsl.core.types.Expression<String> mainYN, com.querydsl.core.types.Expression<String> message) {
        super(AddressDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, addressName, receiver, phone, address1, address2, mainYN, message);
    }

}

