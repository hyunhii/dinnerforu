package com.hyunhii.dinnerForU.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public class MyPageNextSubscribeDto {

    private Long menuId;
    private LocalDate date;
    private int cnt;
    private String dayOfWeek;

    public MyPageNextSubscribeDto(Long id, LocalDate date, int cnt) {
        this.menuId = id;
        this.date = date;
        this.cnt = cnt;
        this.dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
    }
}
