package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private LocalDate subscribeDate;
    private int cnt;
    private String foodName;

    @QueryProjection
    public OrderDto(Long orderId, LocalDateTime orderDate) {
        this.id = orderId;
        this.orderDate = orderDate;
    }

    @QueryProjection
    public OrderDto(Long orderId, LocalDateTime orderDate, int cnt, String foodName) {
        this.id = orderId;
        this.orderDate = orderDate;
        this.cnt = cnt;
        this.foodName = foodName;
    }

    @QueryProjection
    public OrderDto(Long id, LocalDateTime orderDate, LocalDate subscribeDate, int cnt, String foodName) {
        this.id = id;
        this.orderDate = orderDate;
        this.subscribeDate = subscribeDate;
        this.cnt = cnt;
        this.foodName = foodName;
    }
}
