package com.hyunhii.dinnerForU.controller.user.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AddressForm {

    Long addressId;
    String addressName;
    String receiver;
    String phone;
    String address1;
    String address2;
    String mainYN;
    String message;

    public AddressForm(Long addressId, String addressName, String receiver, String phone, String address1, String address2, String mainYN, String message) {
        this.addressId = addressId;
        this.addressName = addressName;
        this.receiver = receiver;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.mainYN = mainYN;
        this.message = message;
    }

    public AddressForm(String addressName, String receiver, String phone, String address1, String address2, String mainYN, String message) {
        this.addressName = addressName;
        this.receiver = receiver;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.mainYN = mainYN;
        this.message = message;
    }

    public AddressForm(String addressName, String receiver, String phone, String address1, String address2, String message) {
        this.addressName = addressName;
        this.receiver = receiver;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.mainYN = mainYN;
        this.message = message;
    }
}
