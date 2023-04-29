package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {
}
