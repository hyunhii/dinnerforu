package com.hyunhii.dinnerForU.controller.admin.form;

import com.hyunhii.dinnerForU.dto.FoodDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MenuForm {

    private Long menuId;
    private int year;
    private int month;
    private int day;
    private LocalDate date;
    private int price;
    private int cnt;
    private List<Long> foodIdList = new ArrayList<>();

}
