package com.hyunhii.dinnerForU.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddressDto {

    private Long id;
    private Long userId;
    private String addressName;
    private String receiver;
    private String phone;
    private String address1;
    private String address2;
    private String mainYN;
    private String message;

    @QueryProjection
    public AddressDto(Long id, String addressName, String receiver, String phone, String address1, String address2, String mainYN, String message) {
        this.id = id;
        this.addressName = addressName;
        this.receiver = receiver;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.mainYN = mainYN;
        this.message = message;
    }
}
