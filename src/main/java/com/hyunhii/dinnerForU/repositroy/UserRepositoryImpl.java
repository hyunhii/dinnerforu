package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.AddressDto;
import com.hyunhii.dinnerForU.dto.QAddressDto;
import com.hyunhii.dinnerForU.entity.QAddress;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


}
