package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.FoodDto;
import com.hyunhii.dinnerForU.dto.FoodSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FoodRepositoryCustom {

    Page<FoodDto> search(Pageable pageable);

    Page<FoodDto> searchFood(FoodSearchCondition condition, Pageable pageable);
}
