package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String addressName;

    private String receiver;

    private String phone;

    private String address1;

    private String address2;

    private String mainYN;

    @Column(columnDefinition = "TEXT")
    private String message;


    public Address(User user, String addressName, String receiver, String phone, String address1, String address2, String mainYN, String message) {
        this.user = user;
        this.addressName = addressName;
        this.receiver = receiver;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.mainYN = mainYN;
        this.message = message;
    }

    public void updateAddress(String addressName, String receiver, String phone, String address1, String address2, String mainYN, String message) {
        this.addressName = addressName;
        this.receiver = receiver;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.mainYN = mainYN;
        this.message = message;
    }

}
