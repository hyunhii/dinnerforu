package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.AddressDto;
import com.hyunhii.dinnerForU.dto.QAddressDto;
import com.hyunhii.dinnerForU.entity.Address;
import com.hyunhii.dinnerForU.entity.User;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.hyunhii.dinnerForU.entity.QAddress.address;

public class AddressRepositoryImpl implements AddressRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public AddressRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }



    @Override
    public List<AddressDto> getList(User user) {

        NumberExpression<Integer> mainYN = new CaseBuilder()
                .when(address.mainYN.eq("Y")).then(1)
                .when(address.mainYN.eq("N")).then(2)
                .otherwise(3);


        return queryFactory
                .select(new QAddressDto(
                        address.id.as("address_id"),
                        address.addressName,
                        address.receiver,
                        address.phone,
                        address.address1,
                        address.address2,
                        address.mainYN,
                        address.message
                )).from(address)
                .where(
                        address.user.eq(user)
                ).orderBy(mainYN.asc())
                .fetch();
    }

    @Override
    public void removeMainAddress(User user) {

        queryFactory
                .update(address)
                .set(address.mainYN ,"N")
                .where(address.user.eq(user)
                        .and(address.mainYN.eq("Y")))
                .execute();

    }

    @Override
    public AddressDto getOne(Address findAddress) {

        return queryFactory
                .select(new QAddressDto(
                        address.id.as("address_id"),
                        address.addressName,
                        address.receiver,
                        address.phone,
                        address.address1,
                        address.address2,
                        address.mainYN,
                        address.message
                )).from(address)
                .where(address.id.eq(findAddress.getId()))
                .fetchOne();
    }

    @Override
    public AddressDto getMainAddress(User user) {

        return queryFactory
                .select(new QAddressDto(
                        address.id.as("address_id"),
                        address.addressName,
                        address.receiver,
                        address.phone,
                        address.address1,
                        address.address2,
                        address.mainYN,
                        address.message
                )).from(address)
                .where(address.user.eq(user)
                        .and(address.mainYN.eq("Y"))
                )
                .fetchOne();
    }

}
