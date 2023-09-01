package com.hyunhii.dinnerForU.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int orderPrice;
    private int count;

    private String addressName;
    private String receiver;
    private String address1;
    private String address2;
    private String message;
    private String phone;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderItem(Menu menu, int price, int cnt, String addressName, String receiver, String address1, String address2, String message, String phone) {
        this.menu = menu;
        this.orderPrice = price;
        this.count = cnt;
        this.addressName = addressName;
        this.receiver = receiver;
        this.address1 = address1;
        this.address2 = address2;
        this.message = message;
        this.phone = phone;
        this.status = OrderStatus.ORDER;
    }

    //생성메서드
    public static OrderItem createOrderMenu(Menu menu, int orderPrice, int count, String addressName, String receiver, String address1, String address2, String message, String phone) {
        OrderItem orderItem = new OrderItem(menu, orderPrice, count, addressName, receiver, address1, address2, message, phone);

        menu.removeStock(count);
        return orderItem;
    }

    //비즈니스로직
    public void cancel() {

        getMenu().addStock(count);
        this.status = OrderStatus.CANCEL;
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


    public void addOrder(Order order) {
        this.order = order;
    }


}

