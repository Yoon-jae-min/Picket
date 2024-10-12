package com.example.picket.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class WishCompositeKey implements Serializable {
    private Customer customer;
    private Performance performance;
}
