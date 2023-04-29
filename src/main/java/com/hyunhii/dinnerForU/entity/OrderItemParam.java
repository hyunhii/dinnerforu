package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemParam {
    private Long menuId;
    private int cnt;

    public OrderItemParam(Long menuId, int cnt) {
        this.menuId = menuId;
        this.cnt = cnt;
    }
}


