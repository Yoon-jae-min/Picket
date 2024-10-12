package com.example.picket.service;

import com.example.picket.entity.Customer;
import com.example.picket.entity.PointHistory;
import com.example.picket.repository.CustomerRepository;
import com.example.picket.repository.PointHistoryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class PointService {

     @Autowired
     private CustomerRepository customerRepository;

    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    @Transactional
    public void save(Long requiredPoint, HttpSession session) {
        Customer currentUser = (Customer)session.getAttribute("customer");
        Customer customer = customerRepository.findById(currentUser.getId()).orElse(null);

        if(customer!= null && requiredPoint!=null){
            customer.setPoint(customer.getPoint()+requiredPoint);
            pointHistoryRepository.save(
                    PointHistory.builder()
                            .pointType("충전")
                            .pointChange(requiredPoint)
                            .customer((Customer)session.getAttribute("customer"))
                    .build()
            );
            customerRepository.save(customer);
            session.setAttribute("customer", customer);
        }
    }
    @Transactional
    public void payment(Long requiredPoint, HttpSession session){
        Customer currentUser = (Customer)session.getAttribute("customer");
        Customer customer = customerRepository.findById(currentUser.getId()).orElse(null);

        if(customer != null && requiredPoint != null){
            pointHistoryRepository.save(
                    PointHistory.builder()
                            .pointType("결제")
                            .pointChange(-requiredPoint)
                            .customer((Customer)session.getAttribute("customer"))
                            .build()
            );
        }
    }

    @Transactional
    public void refund(Long paidPoint, HttpSession session){
        log.info("환불한 금액"+paidPoint);
        Customer currentUser = (Customer) session.getAttribute("customer");

        pointHistoryRepository.save(
                PointHistory.builder()
                        .pointType("환불")
                        .pointChange(paidPoint)
                        .customer((Customer) session.getAttribute("customer"))
                        .build()
        );
        log.info("현재 포인트"+currentUser.getPoint());
        session.setAttribute("customer", currentUser);
    }

    @Transactional
    public List<PointHistory> getAllPointHistory(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        List<String> customerIds = Collections.singletonList(customer.getId());
        return pointHistoryRepository.findAllByCustomerIds(customerIds);
    }
}
