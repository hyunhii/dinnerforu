package com.hyunhii.dinnerForU.controller.user;

import com.hyunhii.dinnerForU.controller.admin.form.NoticeForm;
import com.hyunhii.dinnerForU.dto.NoticeDto;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserNoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String getNoticeList(@AuthenticationPrincipal User user, Model model, Pageable pageable){

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber()-1 ;
        int size = 5;

        Page<NoticeDto> noticeList = noticeService.getListWithPage(page, size);

        model.addAttribute("notices", noticeList);
        model.addAttribute("nowPage", page + 1);
        model.addAttribute("maxPage",5);

        return "user/notice";
    }
}
