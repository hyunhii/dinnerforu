package com.hyunhii.dinnerForU.controller.admin;

import com.hyunhii.dinnerForU.controller.admin.form.FoodForm;
import com.hyunhii.dinnerForU.dto.FoodDto;
import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/admin/food")
    public String createFoodForm(Model model, Pageable pageable) {

        model.addAttribute("form", new FoodForm());

        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber()-1 ;
        int size = 30;

//        List<FoodForm> foodList = foodService.getList();
        Page<FoodDto> foodList = foodService.getListWithPage(page, size);

        model.addAttribute("foods", foodList);
        model.addAttribute("maxPage",5);

        return "admin/food/createFoodForm";
    }

    @PostMapping("/admin/food")
    public String createFood(@ModelAttribute("foodForm")  FoodForm form) {
        Food food = new Food(form.getFoodName(), form.getType(), "Y");

        foodService.createFood(food);

        return "redirect:/admin/food";
    }


    @PostMapping("/admin/food/itemUpload")
    public String createFoodWithImg(@RequestParam MultipartFile file,
                                    @ModelAttribute("foodForm")  FoodForm form) throws Exception {

        String src = null;

        if(!file.isEmpty()) {
//            src = foodService.uploadImg(file);
            src = foodService.uploadToGCP(file);
        }

        Food food = new Food(form.getFoodName(), form.getType(), "Y", src);

        foodService.createFood(food);


        return "redirect:/admin/food";
    }


    @GetMapping("/admin/food/{foodId}/update")
    public String updateFoodForm(@PathVariable("foodId") Long foodId, Model model) {
        FoodDto findFood = foodService.findDto(foodId);

        model.addAttribute("form", findFood);

        return "admin/food/updateFoodForm";
    }

    @PostMapping("/admin/food/{foodId}/update")
    public String updateFood(@PathVariable Long foodId, @ModelAttribute FoodDto form) {
        foodService.updateFood(foodId, form);
        return "redirect:/admin/food";
    }

    @PostMapping("/admin/food/{foodId}/delete")
    public String deleteFood(@PathVariable Long foodId){

        foodService.deleteFood(foodId);

        return "redirect:/admin/food";
    }



}
