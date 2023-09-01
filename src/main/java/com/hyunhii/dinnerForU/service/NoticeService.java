package com.hyunhii.dinnerForU.service;

import com.hyunhii.dinnerForU.controller.admin.form.NoticeForm;
import com.hyunhii.dinnerForU.dto.NoticeDto;
import com.hyunhii.dinnerForU.entity.Notice;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.repositroy.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void createNotice(User user, NoticeForm noticeForm) {

        Notice notice = new Notice(noticeForm.getTitle(), noticeForm.getContent());

        noticeRepository.save(notice);

    }

    public Page<NoticeDto> getListWithPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<NoticeDto> result = noticeRepository.search(pageRequest);

        return result;
    }


}
