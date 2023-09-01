package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.controller.admin.form.FoodForm;
import com.hyunhii.dinnerForU.dto.FoodDto;
import com.hyunhii.dinnerForU.dto.FoodSearchCondition;
import com.hyunhii.dinnerForU.dto.QFoodDto;
import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.entity.FoodType;
import com.hyunhii.dinnerForU.entity.QFood;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.hyunhii.dinnerForU.entity.QFood.food;

public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public FoodRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<FoodDto> search(Pageable pageable) {
        QueryResults<FoodDto> result = queryFactory
                .select(new QFoodDto(
                        food.id.as("foodId"),
                        food.foodName,
                        food.type,
                        food.imgSrc,
                        food.useYN
                )).from(food)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();


        List<FoodDto> content = result.getResults();

        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);

    }


    @Override
    public Page<FoodDto> searchFood(FoodSearchCondition condition, Pageable pageable) {

        QueryResults<FoodDto> result = queryFactory
                .select(new QFoodDto(
                        food.id.as("foodId"),
                        food.foodName,
                        food.type,
                        food.imgSrc,
                        food.useYN
                )).from(food)
                .where(
                        typeEq(condition.getFoodType()),
                        food.useYN.eq("Y")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<FoodDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression typeEq(FoodType foodType) {
        return StringUtils.hasText(String.valueOf(foodType)) ? food.type.eq(foodType) : null;
    }
}
