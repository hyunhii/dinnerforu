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

        /*List<MenuItem> menuItems = new ArrayList<>();

        for (MenuItemParam menuItemParam : menuItemParams) {
            //엔티티조회
            menuItems.removeAll(menuItems);

            System.out.println("menuItems.size() = " + menuItems.size());

            int menuItemSize = menuItemParam.getMenuItemList().size();
            if(menuItemSize > 0)  {
                for (MenuItem menuItem : menuItemParam.getMenuItemList()) {
                    menuItems.add(menuItem);
                }
            }
            LocalDate date = menuItemParam.getDate();
            int cnt = menuItemParam.getCnt();
            int price = menuItemParam.getPrice();

            //메뉴생성
            Menu menu = Menu.createMenu(date, cnt, price, menuItems.toArray(MenuItem[]::new));

            menuRepository.save(menu);

            System.out.println("AFTER : menuItems.size() = " + menuItems.size());
        }*/

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
