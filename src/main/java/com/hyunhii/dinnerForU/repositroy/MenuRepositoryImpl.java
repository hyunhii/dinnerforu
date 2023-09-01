package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.MenuDto;
import com.hyunhii.dinnerForU.dto.QMenuDto;
import com.hyunhii.dinnerForU.entity.FoodType;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static com.hyunhii.dinnerForU.entity.QMenu.menu;
import static com.hyunhii.dinnerForU.entity.QMenuItem.menuItem;

public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MenuRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MenuDto> search(Pageable pageable) {
        QueryResults<MenuDto> result = queryFactory
                .select(new QMenuDto(
                        menu.id.as("menuId"),
                        menu.date,
                        menu.stockQuantity,
                        menu.price
                )).from(menu)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MenuDto> content = result.getResults();

        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<MenuDto> searchByMonth(int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = LocalDate.of(year, month, start.lengthOfMonth());


        List<MenuDto> result = queryFactory.select(new QMenuDto(
                        menu.id.as("menuId"),
                        menu.date,
                        menu.stockQuantity,
                        menu.price,
                        menuItem.food.id,
                        menuItem.food.foodName,
                        menuItem.food.imgSrc
                )).from(menu)
                .join(menu.menuItems, menuItem)
                .where(
                        menu.date.between(start, end)
                )
                .orderBy(menu.date.asc())
                .fetch();


        return result;
    }

    @Override
    public Long checkNextMonthData(LocalDate start, LocalDate end) {


        Long result = queryFactory.select(menu.count())
                .from(menu)
                .where(menu.date.between(start, end))
                .fetchOne();

        return result;
    }


    @Override
    public List<MenuDto> getListOfPeriod(LocalDate start, LocalDate end) {

        NumberExpression<Integer> menuType = new CaseBuilder()
                .when(menuItem.food.type.eq(FoodType.MAIN)).then(1)
                .when(menuItem.food.type.eq(FoodType.DESSERT)).then(2)
                .otherwise(3);

        return  queryFactory.select(new QMenuDto(
                        menu.id.as("menuId"),
                        menu.date,
                        menu.stockQuantity,
                        menu.price,
                        menuItem.food.id,
                        menuItem.food.foodName,
                        menuItem.food.imgSrc
                )).from(menu)
                .join(menu.menuItems, menuItem)
                .where(
                        menu.date.between(start, end)
                )
                .orderBy(menu.date.asc(), menuType.asc())
                .fetch();


    }
}

