package com.hyunhii.dinnerForU.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

    @GetMapping("/notice")
    public String getNoticeList(){
        return "user/notice";
    }
}
