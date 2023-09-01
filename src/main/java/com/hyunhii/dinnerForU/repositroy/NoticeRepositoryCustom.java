package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<NoticeDto> search(Pageable pageable);
}
