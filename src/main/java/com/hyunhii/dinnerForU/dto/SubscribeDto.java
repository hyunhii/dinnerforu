package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class SubscribeDto {

    private Long orderId;
    private Long menuId;
    private int cnt;
    private String address1;
    private LocalDate subscribeDate;
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
        this.orderItemId = orderItemId;
//        this.foodName = foodName;
        this.foodName = foodName.replace(",", " / ");

        this.img = img;

        String[] imgSrc = img.split(",");
        imgSrc1 = imgSrc[0];
        imgSrc2 = imgSrc[1];

    }
}

/*
public interface SubscribeDto {

    Long getOrderId();
    Long getMenuId();
    String getCnt();
    String getAddress();
    LocalDate getSubscribeDate();
    Long getOrderItemId();
    String getFoodName();
    String getImg();
}
*/
