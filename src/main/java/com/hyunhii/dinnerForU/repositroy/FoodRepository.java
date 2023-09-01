package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {
}
