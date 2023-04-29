package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails{

    @Id @Column(name = "user_id")
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private String userName;

    private String password;

    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;


    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Address> addresses = new ArrayList<>();


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
    }

    public User(UserRole role, String id, String userName, String password, String phone, String email) {
        this.role = role;
        this.id = id;
        this.userName = userName;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
        this.phone = phone;
        this.email = email;
        this.status = UserStatus.ING;
    }

    public User(UserRole role, String id, String userName, String password) {
        this.role = role;
        this.id = id;
        this.userName = userName;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.password = passwordEncoder.encode(password);
        this.status = UserStatus.ING;
    }

    //회원정보 수정
    public void changeUserInfo(String userName, String phone, String email) {
        this.userName = userName;
        this.phone = phone;
        this.email = email;
    }

    public void changePW(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    //회원탈퇴
    public void withdrawal() {
        this.userName = "";
        this.phone = "";
        this.email = "";
        this.status = UserStatus.withdrawal;
    }

    //Security

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(this.role.toString()));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정만료여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정잠금여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호만료여부
        return true;
    }

    @Override
    public boolean isEnabled() { //계정활성화여부

        return this.status != UserStatus.withdrawal;
    }
}
