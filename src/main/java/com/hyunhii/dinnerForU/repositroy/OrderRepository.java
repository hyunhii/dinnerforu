package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.SubscribeDto;
import com.hyunhii.dinnerForU.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {


   /* @Query(value =  "SELECT a.ORDER_ID" +
                    "        , b.MENU_ID , COUNT , ADDRESS1" +
                    "        , c.DATE" +
                    "        , ORDER_ITEM_ID" +
                    "        , GROUP_CONCAT(FOOD_NAME), GROUP_CONCAT(IMG_SRC) " +
                    "FROM(" +
                    "   SELECT ORDER_ID " +
                    "   FROM ORDERS " +
                    "   WHERE USER_USER_ID = ? " +
                    "   AND STATUS = 'ORDER' " +
                    ") a " +
                    "LEFT JOIN ORDER_ITEM b " +
                    "ON a.ORDER_ID  = b.ORDER_ID " +
                    "LEFT JOIN MENU c " +
                    "ON b.MENU_ID  = c.MENU_ID " +
                    "LEFT JOIN MENU_ITEM d " +
                    "ON b.MENU_ID  = d.MENU_ID " +
                    "LEFT JOIN FOOD e " +
                    "ON d.FOOD_ID = e.FOOD_ID " +
                    "GROUP BY ORDER_ITEM_ID " +
                    "ORDER BY DATE DESC", nativeQuery = true)
    List<SubscribeDto> getSubscribeList(String UserId);*/

    @Query(name = "getSubscribeList", nativeQuery = true)
    List<SubscribeDto> getSubscribeList(String userId);
}


    /*SELECT a.ORDER_ID
        , b.MENU_ID , COUNT , ADDRESS1
        , c.DATE
        , ORDER_ITEM_ID
        , GROUP_CONCAT(FOOD_NAME ), GROUP_CONCAT(IMG_SRC)
    FROM(
        SELECT ORDER_ID
        FROM ORDERS
        WHERE USER_USER_ID = 'test01'
        AND STATUS = 'ORDER'
        ) a
    LEFT JOIN ORDER_ITEM b
    ON a.ORDER_ID  = b.ORDER_ID
    LEFT JOIN MENU c
    ON b.MENU_ID  = c.MENU_ID
    LEFT JOIN MENU_ITEM d
    ON b.MENU_ID  = d.MENU_ID
    LEFT JOIN FOOD e
    ON d.FOOD_ID = e.FOOD_ID
    GROUP BY ORDER_ITEM_ID
    ORDER BY DATE DESC*/
