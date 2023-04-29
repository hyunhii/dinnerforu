package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.OrderDto;
import com.hyunhii.dinnerForU.dto.OrderItemDto;
import com.hyunhii.dinnerForU.dto.PaySummaryDto;
import com.hyunhii.dinnerForU.entity.Order;
import com.hyunhii.dinnerForU.entity.User;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderDto> getPayList(User user);

    List<OrderItemDto> getOne(Order order);

    List<PaySummaryDto> getOrderSummery(User user);
}
