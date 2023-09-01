package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter @Setter
public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private String dayOfWeek;
    private LocalDate subscribeDate;
    private String sdDayOfWeek;
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
        this.dayOfWeek = orderDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        this.subscribeDate = subscribeDate;
        this.sdDayOfWeek = subscribeDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        this.cnt = cnt;
        this.foodName = foodName;
    }
}
