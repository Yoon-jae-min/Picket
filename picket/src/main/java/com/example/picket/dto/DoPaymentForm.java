package com.example.picket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DoPaymentForm {
    private String ticketCount;
    private String performDate;
    private String payDate;
    private String customerPoint;
    private String performanceTitle;
}
