package com.example.picket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String imp_uid;
    private String merchant_uid;
    @Getter
    private Long amount;
    private String customerId;
    private Long point;

}
