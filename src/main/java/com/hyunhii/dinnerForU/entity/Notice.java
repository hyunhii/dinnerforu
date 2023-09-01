package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notice extends BaseEntity  {

    @Id @GeneratedValue
    private Long id;

//    private String name;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
