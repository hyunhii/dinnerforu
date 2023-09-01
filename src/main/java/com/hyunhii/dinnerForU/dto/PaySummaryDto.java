package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter @Setter
public class PaySummaryDto {

    private Long orderId;
    private LocalDateTime orderDateTime;
    private int amountCnt;
    private int amountPrice;

    private LocalDate orderDate;
    private String dayOfWeek;
    private String amountPriceWithComma;

    @QueryProjection
    public PaySummaryDto(Long orderId, LocalDateTime orderDateTime, int amountCnt, int amountPrice) {
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
        this.amountCnt = amountCnt;
        this.amountPrice = amountPrice;

        this.orderDate= orderDateTime.toLocalDate();
        this.dayOfWeek = orderDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);

        DecimalFormat df=new DecimalFormat("#,###");
        this.amountPriceWithComma = df.format(amountPrice);
    }
}
