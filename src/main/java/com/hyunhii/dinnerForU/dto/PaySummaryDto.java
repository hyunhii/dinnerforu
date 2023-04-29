package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PaySummaryDto {

    private Long orderId;
    private LocalDateTime orderDateTime;
    private int amountCnt;
    private int amountPrice;

    private LocalDate orderDate;
    private String amountPriceWithComma;

    @QueryProjection
    public PaySummaryDto(Long orderId, LocalDateTime orderDateTime, int amountCnt, int amountPrice) {
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
        this.amountCnt = amountCnt;
        this.amountPrice = amountPrice;

        this.orderDate= orderDateTime.toLocalDate();

        DecimalFormat df=new DecimalFormat("#,###");
        this.amountPriceWithComma = df.format(amountPrice);
    }
}
