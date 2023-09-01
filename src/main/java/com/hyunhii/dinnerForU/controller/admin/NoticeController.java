package com.hyunhii.dinnerForU.controller.admin;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/admin/notice")
    public String createForm(Model model, Pageable pageable) {

        model.addAttribute("form", new NoticeForm());

        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber()-1 ;
        int size = 30;

        Page<NoticeDto> noticeList = noticeService.getListWithPage(page, size);

        model.addAttribute("notices", noticeList);

        return "admin/adminNotice";
    }

    @PostMapping("/admin/notice")
    public String createNotice(@AuthenticationPrincipal User user, @ModelAttribute("form") NoticeForm form) {

        noticeService.createNotice(user, form);

        return "redirect:/admin/notice";
    }
}
