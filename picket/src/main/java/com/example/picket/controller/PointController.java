package com.example.picket.controller;

import com.example.picket.entity.*;
import com.example.picket.repository.CustomerRepository;
import com.example.picket.repository.PointHistoryRepository;
import com.example.picket.service.PaymentService;
import com.example.picket.service.PointService;
import com.example.picket.service.QAService;
import com.example.picket.service.WishListService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class PointController {
    @Autowired
    private WishListService wishListService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    @Autowired
    private PointService pointService;
    @Autowired
    private QAService qaService;

    /* 포인트 내역 */
    @GetMapping("/pointlist")
    public String gotopointlist(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");

        List<PointHistory> pointList = pointService.getAllPointHistory(session);
        List<QA> qaList = qaService.getAllQA(session);
        model.addAttribute("pointList", pointList);
        model.addAttribute("qaList", qaList);

        List<WishList> wishListCount = wishListService.WishListFind(customer.getId());
        model.addAttribute("wishList", wishListCount);

        List<Payment> myTicketList = paymentService.findPaymentList(customer.getId());
        model.addAttribute("myTicketList", myTicketList);
        return "/mypage/pointlist";
    }

    @PostMapping("/ChargePoint")
    public String ChargePoint(Long chargeAmount, HttpSession session, RedirectAttributes rttr){
        Customer currentUser = (Customer) session.getAttribute("customer");
        log.info("선택된 충전금액: "+chargeAmount);
        if(currentUser != null){
            pointService.save(chargeAmount, session);
            rttr.addFlashAttribute("success", "포인트가 충전되었습니다.");
            return "redirect:/pointlist";
        }
            rttr.addFlashAttribute("failed", "잘못된 접근입니다. 다시 로그인해주세요.");
            return "redirect:/main";
    }
}
