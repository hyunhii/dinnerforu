package com.hyunhii.dinnerForU.dto;

import com.hyunhii.dinnerForU.entity.PayMethod;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter @Setter
public class OrderItemDto {

    private LocalDateTime orderDateTime;
    private LocalDate orderDate;
    private String dayOfWeek;
    private PayMethod method;
    private LocalDate subscribeDate;
    private String sdDayOfWeek;
    private int cnt;
    private int price;


    @QueryProjection
    public OrderItemDto(LocalDateTime orderDateTime, PayMethod method, LocalDate subscribeDate, int cnt, int price) {
        this.orderDateTime = orderDateTime;
        this.orderDate= orderDateTime.toLocalDate();
        this.dayOfWeek = orderDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);

        this.method = method;
        this.subscribeDate = subscribeDate;
        this.sdDayOfWeek = subscribeDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        this.cnt = cnt;
        this.price = price;
    }
}
