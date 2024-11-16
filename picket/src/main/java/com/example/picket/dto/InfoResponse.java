package com.example.picket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InfoResponse {
    private String url;
    private String sort;
    private int detailNum;
}
