package com.hyunhii.dinnerForU.controller.user;

import com.hyunhii.dinnerForU.controller.user.form.JoinForm;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class LoginController {

    public final UserService userService;

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        @AuthenticationPrincipal User user
                        ) {

        if(user != null) {

            return "redirect:/";
        }

        if(error != null) {

            model.addAttribute("error", error);

            String exceptionMsg = "";
            switch (exception) {
                case "UL01":  case "UL03": case "UL04": case "UL05":
                    exceptionMsg = "일치하는 정보가 없습니다.";
                    break;

                case "UL02":
                    exceptionMsg = "아이디 또는 비밀번호가 일치하지 않습니다";
                    break;
                    
                case "UL07":
                    exceptionMsg = "존재하지 않는 아이디입니다.";
                    break;

            }
            model.addAttribute("exception",exceptionMsg);
        }

        return "user/login";
    }


    //회원가입
    @GetMapping("/join")
    public String joinForm(Model model){

        model.addAttribute("form", new JoinForm());

        return "user/join";
    }

    @PostMapping("join")
    public String join(@ModelAttribute("form") JoinForm joinForm) {

        userService.joinUser(joinForm);

        return "redirect:/join/success";
    }

    //아이디 중복검사
    @PostMapping("/join/checkId")
    @ResponseBody
    public boolean checkDuplicateUserId(@RequestParam Map<String, Object> response) {

        return userService.duplicateCheck_userId(response.get("userId").toString());
    }

    @GetMapping("/join/success")
    public String welcomePage() {

        return "user/welcome";
    }

}
