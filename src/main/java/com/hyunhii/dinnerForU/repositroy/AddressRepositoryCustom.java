package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.AddressDto;
import com.hyunhii.dinnerForU.entity.Address;
import com.hyunhii.dinnerForU.entity.User;

import java.util.List;

public interface AddressRepositoryCustom {
    List<AddressDto> getList(User user);

    void removeMainAddress(User user);

    AddressDto getOne(Address address);

    AddressDto getMainAddress(User user);
}
