package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {
}
