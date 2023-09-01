package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter @Setter
@NoArgsConstructor
public class SubscribeDto {

    private Long orderId;
    private Long menuId;
    private int cnt;
    private String address1;
    private LocalDate subscribeDate;
    private String dayOfWeek;
    private Long orderItemId;
    private String foodName;
    private String img;
    private String imgSrc1;
    private String imgSrc2;


    public SubscribeDto(Long orderId, Long menuId, int cnt, String address1, LocalDate subscribeDate, Long orderItemId, String foodName, String img) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.cnt = cnt;
        this.address1 = address1;
        this.subscribeDate = subscribeDate;
        this.dayOfWeek = this.dayOfWeek = subscribeDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        this.orderItemId = orderItemId;
//        this.foodName = foodName;
        this.foodName = foodName.replace(",", " / ");

        this.img = img;

        String[] imgSrc = img.split(",");
        imgSrc1 = imgSrc[0];
        imgSrc2 = imgSrc[1];

    }
}