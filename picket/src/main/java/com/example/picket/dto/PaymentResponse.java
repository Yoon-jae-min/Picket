package com.example.picket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PaymentResponse {
    private String payDate;
    private String performanceTitle;
    private String performDate;
    private String cancelDate;
    private String ticketCount;
    private String totalPrice;
    private String performanceUrl;
}
