package com.hyunhii.dinnerForU.controller.user;

import com.hyunhii.dinnerForU.controller.user.form.AddressForm;
import com.hyunhii.dinnerForU.controller.user.form.JoinForm;
import com.hyunhii.dinnerForU.dto.*;
import com.hyunhii.dinnerForU.entity.User;
import com.hyunhii.dinnerForU.service.AddressService;
import com.hyunhii.dinnerForU.service.OrderService;
import com.hyunhii.dinnerForU.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserMyPageController {

    private final AddressService addressService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/mypage")
    public String myPageInit(@AuthenticationPrincipal User user, Model model) {

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        int totalCnt = orderService.getTotalSubscribeDate(user.getId());
        model.addAttribute("totalCnt", totalCnt);


        MyPageNextSubscribeDto nextSubscribeInfo = orderService.getNextSubscribeInfo(user.getId());

        String nextDate;
        int nextDateCnt;

        if(nextSubscribeInfo == null) {
            nextDate = "-";
            nextDateCnt = 0;
        } else {
            nextDate = nextSubscribeInfo.getDate() + " (" + nextSubscribeInfo.getDayOfWeek() + ")";
            nextDateCnt = nextSubscribeInfo.getCnt();
        }
        model.addAttribute("nextDate", nextDate);
        model.addAttribute("nextDateCnt", nextDateCnt);

        return "user/myPage";
    }

    @GetMapping("/mypage/mysubscription")
    public String mysubscription(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        List<SubscribeDto> list = orderService.getSubscribeList(user.getId());

        model.addAttribute("list", list);


        return "user/subscription";
    }

    /*결제내역*/
    @GetMapping("/mypage/history/order")
    public String paymentHistory(@AuthenticationPrincipal User user, Model model) {

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        List<OrderDto> payList = orderService.getPayList(user);

        model.addAttribute("list", payList);

        return "user/payHistory";
    }

    @GetMapping("/mypage/cancel/{orderId}")
    public String cancel(@AuthenticationPrincipal User user, Model model, @PathVariable Long orderId) {

        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }


        if(!orderService.checkUser(user.getId(), orderId)) {
            return "redirect:/mypage/history/order";
        }

        List<OrderItemDto> cancelInfo = orderService.getCancelInfo(orderId);

        int amountCnt = 0;
        int amountPrice = 0;
        for (OrderItemDto item : cancelInfo) {
            amountCnt += item.getCnt();
            amountPrice += item.getPrice() * item.getCnt();
        }

        DecimalFormat df=new DecimalFormat("#,###");
        String amountPriceWithComma = df.format(amountPrice);


        model.addAttribute("orderId", orderId);
        model.addAttribute("list", cancelInfo);
        model.addAttribute("amountCnt", amountCnt);
        model.addAttribute("amountPrice", amountPriceWithComma);
        model.addAttribute("method", cancelInfo.get(0).getMethod().getName());


        return "user/cancel";
    }

    /* 환불 */
    @PostMapping("/mypage/cancel")
    public String cancel(@AuthenticationPrincipal User user,
                         @RequestParam("orderId") Long orderId,
                         RedirectAttributes ra) {

        if(!orderService.checkUser(user.getId(), orderId)) {
            ra.addFlashAttribute("message", "cancel");
        } else {
            orderService.cancelOrder(orderId);
            ra.addFlashAttribute("message", "success");
        }

        return "redirect:/mypage/history/order";
    }


    /* 취소내역 */
    @GetMapping("/mypage/history/cancel")
    public String canlHistory(@AuthenticationPrincipal User user,Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }


        List<PaySummaryDto> orderSummery = orderService.getOrderSummery(user);

        model.addAttribute("list", orderSummery);


        return "user/cancelHistory";
    }

    /*결제내역 디테일*/
    @GetMapping("/mypage/history/detail/{orderId}")
    public String orderDetail(@AuthenticationPrincipal User user,
                              @PathVariable Long orderId, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        if(!orderService.checkUser(user.getId(), orderId)) {
            return "redirect:/mypage/history/order";
        }

        List<OrderItemDto> cancelInfo = orderService.getCancelInfo(orderId);

        int amountCnt = 0;
        int amountPrice = 0;
        for (OrderItemDto item : cancelInfo) {
            amountCnt += item.getCnt();
            amountPrice += item.getPrice() * item.getCnt();
        }

        DecimalFormat df=new DecimalFormat("#,###");
        String amountPriceWithComma = df.format(amountPrice);


        model.addAttribute("orderId", orderId);
        model.addAttribute("list", cancelInfo);
        model.addAttribute("amountCnt", amountCnt);
        model.addAttribute("amountPrice", amountPriceWithComma);
        model.addAttribute("method", cancelInfo.get(0).getMethod().getName());
        model.addAttribute("name1", "결제");
        model.addAttribute("name2", "구독");
        model.addAttribute("name3", "결제");

        return "user/detail";
    }

    /*취소내역 디테일*/
    @GetMapping("/mypage/cancel/detail/{orderId}")
    public String cancelDetail(@AuthenticationPrincipal User user,
                               @PathVariable Long orderId, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        if(!orderService.checkUser(user.getId(), orderId)) {
            return "redirect:/mypage/history/cancel";
        }

        List<OrderItemDto> cancelInfo = orderService.getCancelInfo(orderId);

        int amountCnt = 0;
        int amountPrice = 0;
        for (OrderItemDto item : cancelInfo) {
            amountCnt += item.getCnt();
            amountPrice += item.getPrice() * item.getCnt();
        }

        DecimalFormat df=new DecimalFormat("#,###");
        String amountPriceWithComma = df.format(amountPrice);


        model.addAttribute("orderId", orderId);
        model.addAttribute("list", cancelInfo);
        model.addAttribute("amountCnt", amountCnt);
        model.addAttribute("amountPrice", amountPriceWithComma);
        model.addAttribute("method", cancelInfo.get(0).getMethod().getName());
        model.addAttribute("name1", "취소");
        model.addAttribute("name2", "취소");
        model.addAttribute("name3", "환불");


        return "user/detail";
    }




    /* 내 정보 관리 */
    @GetMapping("/mypage/member")
    public String getInfo(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }


        JoinForm userInfo = new JoinForm(user.getId(), user.getUsername(), user.getPhone(), user.getEmail());

        model.addAttribute("form", userInfo);

        return "user/member";
    }

    @PostMapping("/mypage/member/modify")
    public String modifyInfo(@AuthenticationPrincipal User user, @ModelAttribute JoinForm form) {

        userService.changUserInfo(user.getId(), form);

        return "redirect:/mypage/member";
    }

    @PostMapping("/mypage/member/changePW")
    @ResponseBody
    public boolean changePW(@AuthenticationPrincipal User user,@RequestParam Map<String, Object> response) {

        String pw = response.get("pw").toString();
        String newPW = response.get("newPW").toString();

        return userService.changeUserPW(user.getId(), pw, newPW);

    }

    @PostMapping("/mypage/member/withdrawal")
    public String withdrawal(@AuthenticationPrincipal User user){


        userService.withdrawal(user.getId());

        SecurityContextHolder.clearContext();

        return "redirect:/";
    }





    /* 배송지 관리 */
    @GetMapping("/mypage/address")
    public String address(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        List<AddressDto> addressList = addressService.getAddressList(user);

        model.addAttribute("addressList", addressList);


        return "user/address";
    }

    //추가
    @GetMapping("/mypage/address/modify")
    public String addAddress(@AuthenticationPrincipal User user, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }

        model.addAttribute("form", new AddressForm());
;
        return "user/address_modify";
    }

    //수정
    @PostMapping("/mypage/address/modify")
    public String modifyAddress(@AuthenticationPrincipal User user, @RequestParam("data") String addressId, Model model) {
        if(user != null) {
            model.addAttribute("name", user.getUsername());
        }


        if(addressService.checkUser(user.getId(), Long.parseLong(addressId))) {
            AddressDto findAddress = addressService.findAddressOne(Long.parseLong(addressId));

            AddressForm addressForm = new AddressForm(findAddress.getId(), findAddress.getAddressName(), findAddress.getReceiver(), findAddress.getPhone(), findAddress.getAddress1(), findAddress.getAddress2(), findAddress.getMainYN(), findAddress.getMessage());

            model.addAttribute("form", addressForm);
            model.addAttribute("addressId", addressId);
        }

        return "user/address_modify";
    }



    @PostMapping("/mypage/address/edit")
    public String editAddress(@AuthenticationPrincipal User user, @ModelAttribute AddressForm form){

        addressService.addAddress(user, form);

        return "redirect:/mypage/address";
    }


    @PostMapping("/mypage/address/edit/{addressId}")
    public String editAddress(@AuthenticationPrincipal User user, @PathVariable Long addressId, @ModelAttribute AddressForm form){

        addressService.addAddress(user, form);

        return "redirect:/mypage/address";
    }
    
    

    //삭제
    @PostMapping("/mypage/address/delete/{addressId}")
    public String deleteAddress(@AuthenticationPrincipal User user,@PathVariable Long addressId) {

        addressService.deleteAddress(user.getId(),addressId);

        return "redirect:/mypage/address";
    }
}
