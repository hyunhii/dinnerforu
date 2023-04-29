package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.*;
import com.hyunhii.dinnerForU.entity.*;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.hyunhii.dinnerForU.entity.QMenu.menu;
import static com.hyunhii.dinnerForU.entity.QMenuItem.menuItem;
import static com.hyunhii.dinnerForU.entity.QOrder.order;
import static com.hyunhii.dinnerForU.entity.QOrderItem.orderItem;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderDto> getPayList(User user) {

        NumberExpression<Integer> menuType = new CaseBuilder()
                .when(menuItem.food.type.eq(FoodType.MAIN)).then(1)
                .when(menuItem.food.type.eq(FoodType.DESSERT)).then(2)
                .otherwise(3);

        return queryFactory
                .select(new QOrderDto(
                        order.id,
                        order.orderDate,
                        menu.date,
                        orderItem.count,
                        menuItem.food.foodName
                )).from(order)
                .join(order.orderItems, orderItem)
                .join(orderItem.menu, menu)
                .join(menu.menuItems, menuItem)
                .where(order.user.eq(user)
                        .and(order.status.eq(OrderStatus.ORDER)))
                .orderBy(order.orderDate.asc(), menu.date.asc(), menuType.asc())
                .fetch();
    }

    @Override
    public List<OrderItemDto> getOne(Order order) {

        return queryFactory
                .select(new QOrderItemDto(
                        orderItem.order.orderDate,
                        orderItem.order.payMethod,
                        orderItem.menu.date,
                        orderItem.count,
                        orderItem.orderPrice
                ))
                .from(orderItem)
                .join(orderItem.menu, menu)
                .where(orderItem.order.eq(order))
                .orderBy(orderItem.menu.date.asc())
                .fetch();

    }

    @Override
    public List<PaySummaryDto> getOrderSummery(User user) {

        return queryFactory
                .select(new QPaySummaryDto(
                        orderItem.order.id,
                        orderItem.order.orderDate,
                        orderItem.count.sum(),
                        orderItem.order.totalPrice
                ))
                .from(orderItem)
                .where(orderItem.order.user.eq(user)
                        .and(orderItem.order.status.eq(OrderStatus.CANCEL))
                )
                .groupBy(orderItem.order)
                .orderBy(orderItem.order.orderDate.asc())
                .fetch();

    }


}
