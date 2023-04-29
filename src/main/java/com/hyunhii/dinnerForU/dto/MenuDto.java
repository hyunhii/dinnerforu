package com.hyunhii.dinnerForU.dto;

import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.entity.MenuItem;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MenuDto {

    private Long menuId;
    private LocalDate date;
    private int stockQuantity;
    private int price;
    private Long foodId;
    private String foodName;
    private String foodImg;

    @QueryProjection
    public MenuDto(Long id, LocalDate date, int stockQuantity, int price) {
        this.menuId = id;
        this.date = date;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }


    @QueryProjection
    public MenuDto(Long id, LocalDate date, int stockQuantity, int price, Long foodId, String foodName) {
        this.menuId = id;
        this.date = date;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.foodId = foodId;
        this.foodName = foodName;
    }

    @QueryProjection
    public MenuDto(Long id, LocalDate date, int stockQuantity, int price, Long foodId, String foodName, String foodImg) {
        this.menuId = id;
        this.date = date;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImg = foodImg;
    }
}
