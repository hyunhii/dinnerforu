package com.hyunhii.dinnerForU.entity;

import com.hyunhii.dinnerForU.dto.MyPageNextSubscribeDto;
import com.hyunhii.dinnerForU.dto.SubscribeDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Orders")
@NamedNativeQuery(
        name = "getSubscribeList",
        query = "SELECT a.ORDER_ID" +
                "        , b.MENU_ID , COUNT , ADDRESS1" +
                "        , c.DATE" +
                "        , ORDER_ITEM_ID" +
                "        , GROUP_CONCAT(FOOD_NAME ORDER BY (case when TYPE= 'MAIN' then 1 when TYPE='DESSERT' then 2 ELSE 4 end )) as food_name" +
                "        , GROUP_CONCAT(IMG_SRC ORDER BY (case when TYPE= 'MAIN' then 1 when TYPE='DESSERT' then 2 ELSE 4 end )) as img_src " +
                "FROM(" +
                "   SELECT ORDER_ID " +
                "   FROM orders " +
                "   WHERE USER_ID = ? " +
                "   AND STATUS = 'ORDER' " +
                ") a " +
                "LEFT JOIN order_item b " +
                "ON a.ORDER_ID  = b.ORDER_ID " +
                "LEFT JOIN menu c " +
                "ON b.MENU_ID  = c.MENU_ID " +
                "LEFT JOIN menu_item d " +
                "ON b.MENU_ID  = d.MENU_ID " +
                "LEFT JOIN food e " +
                "ON d.FOOD_ID = e.FOOD_ID " +
                "GROUP BY ORDER_ITEM_ID " +
                "ORDER BY DATE DESC",
        resultSetMapping = "test"
)
@SqlResultSetMapping(
        name = "test",
        classes = @ConstructorResult(
                targetClass = SubscribeDto.class,
                columns = {
                        @ColumnResult(name = "order_id", type = Long.class),
                        @ColumnResult(name = "menu_id", type = Long.class),
                        @ColumnResult(name = "count", type = int.class),
                        @ColumnResult(name = "address1", type = String.class),
                        @ColumnResult(name = "date", type = LocalDate.class),
                        @ColumnResult(name = "order_item_id", type = Long.class),
                        @ColumnResult(name = "food_name", type = String.class),
                        @ColumnResult(name = "img_src", type = String.class)
                }
        )
)
@NamedNativeQuery(name = "getNextSubscribeDateMenuId",
        query = "SELECT c.MENU_ID, c.DATE, SUM(COUNT) AS COUNT " +
                "FROM (" +
                "       SELECT ORDER_ID " +
                "       FROM orders " +
                "       WHERE USER_ID = ? AND STATUS = 'ORDER' " +
                ") a " +
                "LEFT JOIN order_item b " +
                "ON a.ORDER_ID = b.ORDER_ID " +
                "LEFT JOIN menu c " +
                "ON b.MENU_ID = c.MENU_ID " +
                "WHERE c.DATE > CURDATE() " +
                "GROUP BY MENU_ID " +
                "ORDER BY c.DATE ASC " +
                "LIMIT 1",
        resultSetMapping = "test2"
)
@SqlResultSetMapping(
        name = "test2",
        classes = @ConstructorResult(
                targetClass = MyPageNextSubscribeDto.class,
                columns = {
                        @ColumnResult(name = "menu_id", type = Long.class),
                        @ColumnResult(name = "date", type = LocalDate.class),
                        @ColumnResult(name = "count", type = Integer.class)
                }
        )
)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;
    private int totalPrice;
    private int discountPrice;
    private int paymentPrice;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;
    //private couponList

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public Order(int totalPrice) {
        this.totalPrice = totalPrice;
        this.paymentPrice = totalPrice;
    }

    // 연관관계 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

    private void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void createOrderDate() {
        this.orderDate = LocalDateTime.now();
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setPrice(int price) {
        this.totalPrice = price;
        this.paymentPrice = price;
    }


    //생성메서드
    public static Order createOrder(User user, PayMethod payMethod, OrderItem... orderItems) {
        Order order = new Order();
        order.setUser(user);

        int amountPrice = 0;

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
            amountPrice += orderItem.getCount() * orderItem.getOrderPrice();
        }

        order.createOrderDate();
        order.setStatus(OrderStatus.ORDER);

        order.setPayMethod(payMethod);
        order.setPrice(amountPrice);

        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

    public void cancelOrder() {
        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public void cancel() {

        this.status = OrderStatus.CANCEL;

        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
