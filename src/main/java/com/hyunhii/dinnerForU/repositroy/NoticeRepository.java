package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {



}
