package com.hyunhii.dinnerForU.controller.user;

import com.hyunhii.dinnerForU.controller.user.form.AddressForm;
import com.hyunhii.dinnerForU.dto.AddressDto;
import com.hyunhii.dinnerForU.dto.MenuDto;
import com.hyunhii.dinnerForU.entity.OrderItemParam;
import com.hyunhii.dinnerForU.entity.PayMethod;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.service.AddressService;
import com.hyunhii.dinnerForU.service.MenuService;
import com.hyunhii.dinnerForU.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ApplyMenuController {

    private final MenuService menuService;
    private final OrderService orderService;
    private final AddressService addressService;

    @GetMapping("/applyMenu")
    public String createApplyForm(@AuthenticationPrincipal User user, Model model) {

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        LocalDate today = LocalDate.now();

        LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfNextMonth = today.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        int totalCnt = cntOfWeekdays(firstDayOfNextMonth, lastDayOfNextMonth);

        boolean nextMonth = menuService.checkNextMonthDate(totalCnt, firstDayOfNextMonth, lastDayOfNextMonth);

        LocalDate lastDate = nextMonth ? lastDayOfNextMonth : today.with(TemporalAdjusters.lastDayOfMonth());

        List<MenuDto> menuList = menuService.getMenuStartingThisWeek(today, lastDate);

        model.addAttribute("menuList", menuList);
        model.addAttribute("nextMonth", nextMonth);

        return "user/applyMenu";
    }

    @PostMapping("/applyMenu/pay")
//    public String receiveApplyList(Model model, @RequestParam Map<String, Object> response) {
    public String receiveApplyList(@AuthenticationPrincipal User user, Model model, @RequestParam("applyList") String list) {

        if(list == null) {
            return "redirect:/applyMenu";
        }

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        AddressDto mainAddress = addressService.getMainAddressList(user);

        AddressForm addressForm;

        if(mainAddress != null) {
            addressForm = new AddressForm(mainAddress.getAddressName(), mainAddress.getReceiver(), mainAddress.getPhone(), mainAddress.getAddress1(), mainAddress.getAddress2(), mainAddress.getMessage());
        } else {
            addressForm = new AddressForm();
        }

        model.addAttribute("applyList", list);
        model.addAttribute("addressForm", addressForm);

        return "user/pay";
    }

    @GetMapping("/applyMenu/pay")
    public String payPage(@AuthenticationPrincipal User user, Model model) {

        return "redirect:/applyMenu";
    }

    @PostMapping("/order")
    public String subscribe(@AuthenticationPrincipal User user, Model model, 
                            @RequestParam("applyList") String list,
                            @RequestParam("payMethod")String method,
                            RedirectAttributes ra) {

        PayMethod payMethod;

        if(method.equals("card")) {
            payMethod = PayMethod.CREDIT_CARD;
        } else if(method.equals("account")) {
            payMethod = PayMethod.ACCOUNT;
        } else {
            throw new IllegalArgumentException("올바른 입력이 아닙니다.");
        }


        JSONArray jsonArr = new JSONArray(list);

        String userId = user.getId();

        List<OrderItemParam> orderItems = new ArrayList<>();

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);

            OrderItemParam orderItemParam = new OrderItemParam(Long.parseLong(jsonObj.get("id").toString()), Integer.parseInt(jsonObj.get("cnt").toString()));

            orderItems.add(orderItemParam);
        }

        orderService.order(userId, payMethod, orderItems.toArray(OrderItemParam[]::new));

        ra.addFlashAttribute("result", true);

        return "redirect:/applyMenu/pay/complete";
    }

    @GetMapping("/applyMenu/pay/complete")
    public String payComplete(@AuthenticationPrincipal User user, Model model, HttpServletRequest request, RedirectAttributes ra) {

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap != null) {
            String message = flashMap.get("result").toString();

            return "user/payComplete";
        } else {

            ra.addFlashAttribute("message", "payError");

            return "redirect:/applyMenu";
        }



    }


    private int cntOfWeekdays(LocalDate start, LocalDate end) {

        int cnt = 0;

        while (start.isBefore(end.plusDays(1))) {
            if (start.getDayOfWeek() != DayOfWeek.SATURDAY && start.getDayOfWeek() != DayOfWeek.SUNDAY) {
                cnt++;
            }

            start = start.plusDays(1);
        }

        return cnt;

    }
}
