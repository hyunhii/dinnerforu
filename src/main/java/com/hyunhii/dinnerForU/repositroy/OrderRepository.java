package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.MyPageNextSubscribeDto;
import com.hyunhii.dinnerForU.dto.SubscribeDto;
import com.hyunhii.dinnerForU.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    @Query(name = "getSubscribeList", nativeQuery = true)
    List<SubscribeDto> getSubscribeList(String userId);

    @Query(value =  "SELECT count(DISTINCT b.menu_id) " +
                    "FROM ( " +
                    "       SELECT ORDER_ID " +
                    "       FROM orders " +
                    "       WHERE USER_ID = ? AND STATUS = 'ORDER' " +
                    ") a " +
                    "LEFT JOIN order_item b " +
                    "ON a.order_id = b.order_id"
    , nativeQuery = true)
    int getTotalSubscribeDate(String userId);

    @Query(name = "getNextSubscribeDateMenuId", nativeQuery = true)
    MyPageNextSubscribeDto getNextSubscribeDateMenuId(String userId);

}
