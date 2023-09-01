package com.hyunhii.dinnerForU.service;

import com.hyunhii.dinnerForU.controller.user.form.AddressForm;
import com.hyunhii.dinnerForU.dto.AddressDto;
import com.hyunhii.dinnerForU.entity.Address;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.repositroy.AddressRepository;
import com.hyunhii.dinnerForU.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    //주소추가
    @Transactional
    public void addAddress(User user, AddressForm addressForm) {

        String phone = addressForm.getPhone().replaceAll("[^0-9]", "");
        String mainYN = addressForm.getMainYN().equals("") ? "N" : addressForm.getMainYN();


        if(addressForm.getAddressId() == null) {

            if(mainYN.equals("Y")) {
                addressRepository.removeMainAddress(user);
            } else {
                if(addressRepository.getList(user).size() ==0) {
                    mainYN = "Y";
                }
            }

            Address address = new Address(user, addressForm.getAddressName(), addressForm.getReceiver(), phone, addressForm.getAddress1(), addressForm.getAddress2(), mainYN, addressForm.getMessage());

            addressRepository.save(address);

        } else {

            Address address = addressRepository.findById(addressForm.getAddressId()).orElseThrow();

            if(mainYN.equals("Y")) {
                addressRepository.removeMainAddress(user);
            } else {
                if(addressRepository.getList(user).size() == 0 || addressRepository.getList(user).size() == 1) {
                    mainYN = "Y";
                }
            }

            address.updateAddress(addressForm.getAddressName(), addressForm.getReceiver(), addressForm.getPhone(), addressForm.getAddress1(), addressForm.getAddress2(), mainYN, addressForm.getMessage());
        }



    }


    public List<AddressDto> getAddressList(User user) {

        return addressRepository.getList(user);
    }


    public AddressDto getMainAddressList(User user) {

        return addressRepository.getMainAddress(user);

    }



    @Transactional
    public void deleteAddress(String loggedInUserId, Long addressId) {

        User user1 = userRepository.findById(loggedInUserId).get(0);
        Address address = addressRepository.findById(addressId).orElseThrow();

        boolean checkedUser = address.getUser().equals(user1);

        if(checkedUser) {
            addressRepository.delete(address);
        } else {

            throw new IllegalArgumentException("해당 배송지내역을 지울 수 없습니다.");
        }

    }

    public boolean checkUser(String userId, Long addressId) {
        User user1 = userRepository.findById(userId).get(0);
        Address address = addressRepository.findById(addressId).orElseThrow();

        if(address.getUser().equals(user1)) {
           return true;
        } else {
            throw new IllegalArgumentException("해당 배송지내역을 수정할 수 없습니다.");
        }
    }

    public AddressDto findAddressOne(Long addressId){
        Address address = addressRepository.findById(addressId).orElseThrow();

        return addressRepository.getOne(address);
    }
}
