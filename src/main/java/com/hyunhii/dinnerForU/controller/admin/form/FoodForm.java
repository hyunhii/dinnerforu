package com.hyunhii.dinnerForU.controller.admin.form;

import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.entity.FoodType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
public class FoodForm {

    private Long foodId;
    private String foodName;
    private FoodType type;
    private String useYN;
//    private MultipartFile file;

    public FoodForm(Food food) {
        this.foodId = food.getId();
        this.foodName = food.getFoodName();
        this.type = food.getType();
        this.useYN = food.getUseYN();
    }

}
