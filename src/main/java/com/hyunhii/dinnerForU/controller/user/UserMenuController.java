package com.hyunhii.dinnerForU.controller.user;

import com.hyunhii.dinnerForU.dto.MenuDto;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserMenuController {

    private final MenuService menuService;


    @GetMapping("/menu")
    public String getMenuList(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        LocalDate today = LocalDate.now();

        LocalDate thisMonday = today.getDayOfWeek().getValue() == 6 || today.getDayOfWeek().getValue() == 7 ?
                today.with(TemporalAdjusters.next(DayOfWeek.MONDAY)) : today.with(DayOfWeek.MONDAY);

        LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfNextMonth = today.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());


        int totalCnt = cntOfWeekdays(firstDayOfNextMonth, lastDayOfNextMonth);

        boolean nextMonth = menuService.checkNextMonthDate(totalCnt, firstDayOfNextMonth, lastDayOfNextMonth);

        LocalDate lastDate = nextMonth ? lastDayOfNextMonth : today.with(TemporalAdjusters.lastDayOfMonth());

        List<MenuDto> menuList = menuService.getMenuStartingThisWeek(thisMonday, lastDate);


        List<String> weekDays = getWeekDays(nextMonth, today, thisMonday);
        model.addAttribute("weekDays", weekDays);
        model.addAttribute("menuList", menuList);

        return "user/menu";
    }




    private List<String> getWeekDays(boolean nextMonth, LocalDate today, LocalDate date) {

        LocalDate lastDate;

        lastDate = nextMonth ?
                today.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()) :
                today.with(TemporalAdjusters.lastDayOfMonth());


        String str = "";
        List<String> weekDay = new ArrayList<>();

        while (date.isBefore(lastDate.plusDays(1))) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {

                if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                    str += date.getMonthValue() + "월 " + date.getDayOfMonth() + "일";
                    if (date.isEqual(lastDate)) {
                        weekDay.add(str);
                        str = "";
                    }

                } else if (date.getDayOfWeek() == DayOfWeek.FRIDAY || date.isEqual(lastDate) ) {
                    str += " ~ " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일";
                    weekDay.add(str);
                    str = "";
                }

            }
            date = date.plusDays(1);
        }

        return weekDay;
    }

    private int cntOfWeekdays(LocalDate start, LocalDate end) {

       int cnt = 0;

        while (start.isBefore(end.plusDays(1))) {
            if (start.getDayOfWeek() != DayOfWeek.SATURDAY && start.getDayOfWeek() != DayOfWeek.SUNDAY) {
                cnt++;
            }

            start = start.plusDays(1);
        }

        return cnt;

    }

}
