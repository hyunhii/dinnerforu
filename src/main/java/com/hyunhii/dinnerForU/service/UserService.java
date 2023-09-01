package com.hyunhii.dinnerForU.service;

import com.hyunhii.dinnerForU.controller.user.form.JoinForm;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.entity.UserRole;
import com.hyunhii.dinnerForU.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> user = userRepository.findById(username);

        if(user.isEmpty()) {
            throw  new UsernameNotFoundException("Not Found account.");
        }

        return user.get(0);
    }

    @Transactional
    public void joinUser(JoinForm form) {

        String phone = form.getPhone().replaceAll("[^0-9]", "");

        List<User> checkDuplicateId = userRepository.findById(form.getUserId());
        List<User> checkDuplicateEmail = userRepository.findByEmail(form.getEmail());
        List<User> checkDuplicatePhone = userRepository.findByPhone(phone);
        if(checkDuplicateId.size() > 0 || checkDuplicatePhone.size() > 0 ||checkDuplicateEmail.size()>0) {
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }

        //아무것도 입력하지 않았을때 예외처리

        User newUser = new User(UserRole.ROLE_USER,  form.getUserId(), form.getName(), form.getPassword(), phone, form.getEmail());

        userRepository.save(newUser);
    }


    @Transactional
    public void changUserInfo(String userId, JoinForm form) {
        User user = userRepository.findById(userId).get(0);
        String phone = form.getPhone().replaceAll("[^0-9]", "");

        if(!user.getPhone().equals(phone) && !duplicateCheck_phone(phone)) {
            throw new IllegalArgumentException("중복된 연락처입니다.");
        }

        if(!user.getEmail().equals(form.getEmail()) && !duplicateCheck_email(form.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }

        if(form.getName().equals(user.getUsername())
                && phone.equals(user.getPhone())
                && form.getEmail().equals(user.getEmail())) {
            return;
        }

        user.changeUserInfo(form.getName(), form.getPhone(), form.getEmail());

    }


    @Transactional
    public boolean changeUserPW(String userId, String password, String newPassword) {
        User user = userRepository.findById(userId).get(0);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())) {
            user.changePW(newPassword);
            return  true;
        } else {
            return false;
        }

    }

    @Transactional
    public void withdrawal(String userId) {
        User user = userRepository.findById(userId).get(0);

        user.withdrawal();
    }


    public boolean duplicateCheck_userId(String userId) {
        List<User> userList = userRepository.findById(userId);

        return userList.size() == 0;
    }

    public boolean duplicateCheck_phone(String phone) {
        List<User> userList = userRepository.findByPhone(phone);

        return userList.size() == 0;
    }

    public boolean duplicateCheck_email(String email) {
        List<User> userList = userRepository.findByEmail(email);

        return userList.size() == 0;
    }
}
