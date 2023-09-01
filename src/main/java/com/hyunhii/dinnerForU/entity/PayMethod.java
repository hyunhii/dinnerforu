package com.hyunhii.dinnerForU.entity;

import lombok.Getter;

@Getter
public enum PayMethod {
    CREDIT_CARD("신용카드"), ACCOUNT("가상계좌");

    private String name;

    PayMethod(String name) {
        this.name = name;
    }
}
