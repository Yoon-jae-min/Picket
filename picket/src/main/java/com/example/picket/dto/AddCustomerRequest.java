package com.example.picket.dto;

import lombok.Data;

@Data
public class AddCustomerRequest {
    private String id;
    private String password;
    private String email;
    private String name;
    private String birthdate;
    private String tel;
    private Long point;


}
