package com.example.picket.controller;

import com.example.picket.dto.AddWishRequest;
import com.example.picket.dto.PerformanceForm;
import com.example.picket.entity.Customer;
import com.example.picket.service.PerformanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class SearchController {
    private final PerformanceService performanceService;

    @PostMapping("/performanceDate")
    public ResponseEntity<PerformanceForm> perfomanceDate(@RequestBody AddWishRequest addWishRequest){
        PerformanceForm performanceForm = performanceService.findInfo(addWishRequest.getTitle());

        return ResponseEntity.ok(performanceForm);
    }

    @GetMapping("/customerPoint")
    public ResponseEntity<Long> customerPoint(HttpServletRequest request){
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        return ResponseEntity.ok(customer.getPoint());
    }
}
