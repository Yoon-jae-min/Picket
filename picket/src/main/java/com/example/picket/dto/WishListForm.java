package com.example.picket.dto;

import com.example.picket.entity.Customer;
import com.example.picket.entity.Performance;
import com.example.picket.entity.WishList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class WishListForm {
    private Performance performance;
    private Customer customer;

    public WishList toEntity() { return new WishList(performance,customer);}
}
