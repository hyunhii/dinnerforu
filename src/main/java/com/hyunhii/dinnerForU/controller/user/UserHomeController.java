package com.hyunhii.dinnerForU.controller.user;

import com.hyunhii.dinnerForU.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserHomeController {

    @GetMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model, HttpSession session) {

        if(user != null) {
            model.addAttribute("name", user.getUsername());

            session.setAttribute("user_id", user.getId());
        }

        boolean init = false;

        return "user/index";
    }

}
