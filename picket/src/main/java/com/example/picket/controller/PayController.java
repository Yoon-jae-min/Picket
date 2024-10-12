package com.example.picket.controller;

import com.example.picket.dto.DeletePayContentForm;
import com.example.picket.dto.DeletePayRequestForm;
import com.example.picket.dto.DoPaymentForm;
import com.example.picket.dto.PaymentRequest;
import com.example.picket.entity.Customer;
import com.example.picket.entity.Ticket;
import com.example.picket.repository.PerformanceRepository;
import com.example.picket.repository.PointHistoryRepository;
import com.example.picket.service.CustomerService;
import com.example.picket.service.PaymentService;
import com.example.picket.service.PointService;
import com.example.picket.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

import static java.lang.Long.parseLong;

@Controller
@RequiredArgsConstructor
public class PayController {

    private final TicketService ticketService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final PerformanceRepository performanceRepository;
    private final PointService pointService;

    @PostMapping("/doPay")
    public ResponseEntity<Void> doPay(HttpServletRequest request,HttpSession session, @RequestBody DoPaymentForm doPaymentForm){
        System.out.println("결제확인: " + doPaymentForm.toString());
        String performanceTitle = doPaymentForm.getPerformanceTitle();
        Long currentPerformancePrice = parseLong(doPaymentForm.getTicketCount()) * performanceRepository.findById(performanceTitle).orElse(null).getPrice();

        Customer customer = (Customer) session.getAttribute("customer");
        Long currentUserBalance = customer.getPoint();

        if(currentUserBalance >= currentPerformancePrice && currentUserBalance >= 0){
            customerService.pointUpdate(doPaymentForm.getCustomerPoint(), customer.getId());
            pointService.payment(currentPerformancePrice, session);
            customer.setPoint(parseLong(doPaymentForm.getCustomerPoint()));
            session.setAttribute("customer", customer);


            List<Ticket> tickets = ticketService.ticketCreateInput(doPaymentForm.getTicketCount(), doPaymentForm.getPerformanceTitle());
            if(!tickets.isEmpty()){
                paymentService.paymentInfoInput(tickets, customer, doPaymentForm);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/deletePayment")
    public void deletePayment(@RequestBody DeletePayRequestForm deletePayRequestForm, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");

        Long refundedPrice = paymentService.deletePayment(customer.getId(), deletePayRequestForm);
        pointService.refund(refundedPrice, session);

        customer.setPoint(customerService.getPoint(customer.getId()));
        session.setAttribute("customer", customer);
    }
}
