package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuItemParam {
//    private List<MenuItem> menuItemList;
    private List<Long> foodIdList;
    private LocalDate date;
    private int price;
    private int cnt;

    public MenuItemParam(List<Long> foodIdList, LocalDate date, int price, int cnt) {
        this.foodIdList = foodIdList;
        this.date = date;
        this.price = price;
        this.cnt = cnt;
    }


}


