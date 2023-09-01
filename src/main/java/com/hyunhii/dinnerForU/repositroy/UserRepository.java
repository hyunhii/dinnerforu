package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    List<User> findByEmailAndPassword(String email, String password);

    List<User> findByEmail(String email);

    List<User> findById(String id);

    List<User> findByPhone(String phone);
}
