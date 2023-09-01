package com.hyunhii.dinnerForU.service;

import com.hyunhii.dinnerForU.controller.admin.form.MenuForm;
import com.hyunhii.dinnerForU.dto.MenuDto;
import com.hyunhii.dinnerForU.entity.*;
import com.hyunhii.dinnerForU.repositroy.FoodRepository;
import com.hyunhii.dinnerForU.repositroy.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;


    //메뉴표 작성
    @Transactional
    public void makeList(MenuItemParam... menuItemParams) {

        List<MenuItem> menuItemList= new ArrayList<>();

        for (MenuItemParam menuItemParam : menuItemParams) {

            List<Long> foodIdList = menuItemParam.getFoodIdList();

            if(foodIdList.size() > 0) {
                for (Long foodId : foodIdList) {
                    //food 엔티티 조회
                    Food food = foodRepository.findById(foodId).orElseThrow();

                    //MenuItem 생성
                    MenuItem menuItem = new MenuItem(food);
                    menuItemList.add(menuItem);
                }
            }

            //메뉴 생성
            Menu menu = Menu.createMenu(menuItemParam.getDate(), menuItemParam.getCnt(), menuItemParam.getPrice(), menuItemList.toArray(MenuItem[]::new));

            menuRepository.save(menu);
        }

    }
    public List<MenuDto> getList(int year, int month) {
        List<MenuDto> result = menuRepository.searchByMonth(year, month);

        return result;
    }

    public Page<MenuDto> getListwithPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MenuDto> result = menuRepository.search(pageRequest);

        return result;
    }

    public boolean checkNextMonthDate(int totalCnt, LocalDate start, LocalDate end) {
        Long cnt = menuRepository.checkNextMonthData(start, end);

        return totalCnt == cnt;
    }

    public List<MenuDto> getMenuStartingThisWeek(LocalDate start, LocalDate end) {
        return menuRepository.getListOfPeriod(start, end);
    }
}
