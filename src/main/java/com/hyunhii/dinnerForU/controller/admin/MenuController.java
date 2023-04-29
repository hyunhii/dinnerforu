package com.hyunhii.dinnerForU.controller.admin;

import com.hyunhii.dinnerForU.controller.admin.form.MenuForm;
import com.hyunhii.dinnerForU.dto.FoodDto;
import com.hyunhii.dinnerForU.dto.MenuDto;
import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.entity.FoodType;
import com.hyunhii.dinnerForU.entity.MenuItem;
import com.hyunhii.dinnerForU.entity.MenuItemParam;
import com.hyunhii.dinnerForU.service.FoodService;
import com.hyunhii.dinnerForU.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final FoodService foodService;

    @GetMapping("/admin/menu")
    public String createMenuForm(Model model, Pageable pageable) {

        model.addAttribute("menuForm", new MenuForm());

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        List<MenuDto> menuList = menuService.getList(year, month);
        model.addAttribute("menuList", menuList);

        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber()-1 ;
        int size = 30;

        Page<FoodDto> mainFoodList = foodService.getFoodList(FoodType.MAIN, page, size);
        Page<FoodDto> dessertList = foodService.getFoodList(FoodType.DESSERT, page, size);

        model.addAttribute("mainFoods", mainFoodList);
        model.addAttribute("desserts", dessertList);

        model.addAttribute("defaultPrice", 8000);
        model.addAttribute("defaultCnt", 100);

        model.addAttribute("maxPage", 5);

        return "admin/menu/createMenuForm";
    }

    @PostMapping("/admin/menu")
    public String createMenu(@ModelAttribute("menuForm") MenuForm menuForm) {
        List<Long> foodIdList = menuForm.getFoodIdList();

        LocalDate date = LocalDate.of(menuForm.getYear(), menuForm.getMonth(), menuForm.getDay());
        int price = menuForm.getPrice();
        int stockQuantity = menuForm.getCnt();

        MenuItemParam menuItemParam = new MenuItemParam(foodIdList, date, price, stockQuantity);

        menuService.makeList(menuItemParam);

        return "redirect:/admin/menu";
    }
}
