package com.hyunhii.dinnerForU.service;

import com.hyunhii.dinnerForU.dto.*;
import com.hyunhii.dinnerForU.entity.*;
import com.hyunhii.dinnerForU.repositroy.AddressRepository;
import com.hyunhii.dinnerForU.repositroy.MenuRepository;
import com.hyunhii.dinnerForU.repositroy.OrderRepository;
import com.hyunhii.dinnerForU.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;


    // 주문
    @Transactional
    public Long order(String userId, PayMethod method, OrderItemParam... orderItemParams) {

        //유저조회
        User findUser = userRepository.findById(userId).get(0);
        AddressDto mainAddress = addressRepository.getMainAddress(findUser);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemParam orderItemParam : orderItemParams) {
            //엔티티 조회
            Optional<Menu> menu = menuRepository.findById(orderItemParam.getMenuId());

            //주문상품 생성
            OrderItem orderItem = OrderItem.createOrderMenu(
                    menu.get(), menu.get().getPrice(), orderItemParam.getCnt(),
                    mainAddress.getAddressName(), mainAddress.getReceiver(), mainAddress.getAddress1(), mainAddress.getAddress2(), mainAddress.getMessage(), mainAddress.getPhone()
                    );

            orderItems.add(orderItem);
        }


//        주문
        Order order = Order.createOrder(findUser, method, orderItems.toArray(OrderItem[]::new));

        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.cancel();
    }


    /*결제내역*/
    public List<OrderDto> getPayList(User user) {

        return orderRepository.getPayList(user);

    }

    public List<PaySummaryDto> getOrderSummery(User user) {


        return orderRepository.getOrderSummery(user);
    }



    public List<SubscribeDto> getSubscribeList(String userId) {
        return orderRepository.getSubscribeList(userId);
    }

    public int getTotalSubscribeDate(String userId) {
        return orderRepository.getTotalSubscribeDate(userId);
    }

    public MyPageNextSubscribeDto getNextSubscribeInfo(String userId) {
        return orderRepository.getNextSubscribeDateMenuId(userId);

    }




    
    public boolean checkUser(String userId, Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);


        if(order.isEmpty())  {
            return false;
        } else {
            Order findOrder = order.orElseThrow();
            User user = userRepository.findById(userId).get(0);

            return findOrder.getUser().equals(user);
        }

    }

    public List<OrderItemDto>  getCancelInfo(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow();


        return orderRepository.getOne(order);

    }
}
