package com.hyunhii.dinnerForU.dto;

import com.hyunhii.dinnerForU.entity.PayMethod;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class OrderItemDto {

    LocalDateTime orderDateTime;
    LocalDate orderDate;
    PayMethod method;
    LocalDate subscribeDate;
    int cnt;
    int price;


    @QueryProjection
    public OrderItemDto(LocalDateTime orderDateTime, PayMethod method, LocalDate subscribeDate, int cnt, int price) {
        this.orderDateTime = orderDateTime;
        this.orderDate= orderDateTime.toLocalDate();

        this.method = method;
        this.subscribeDate = subscribeDate;
        this.cnt = cnt;
        this.price = price;
    }
}
