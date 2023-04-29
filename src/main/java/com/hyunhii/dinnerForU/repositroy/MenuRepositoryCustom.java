package com.hyunhii.dinnerForU.repositroy;

import com.hyunhii.dinnerForU.dto.MenuDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepositoryCustom {

    Page<MenuDto> search(Pageable pageable);

    List<MenuDto> searchByMonth(int year, int month);

    Long checkNextMonthData(LocalDate start, LocalDate end);

    List<MenuDto> getListOfPeriod(LocalDate start, LocalDate end);
}
