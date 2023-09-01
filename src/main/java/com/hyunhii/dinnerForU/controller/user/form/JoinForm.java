package com.hyunhii.dinnerForU.controller.user.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class JoinForm {

    String userId;
    String password;
    String passwordCheck;
    String name;
    String phone;
    String email;

    public JoinForm(String userId, String password, String passwordCheck, String name, String phone, String email) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public JoinForm(String userId, String name, String phone, String email) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
